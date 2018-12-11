#!/bin/bash
#这个脚本使用来统计CPU、磁盘、内存使用率、带宽的，存入数据库---每一分钟采集一次
# 默认bash shell不能直接运算小数点，所以需要借助bc
# bc命令是一种支持任意精度的交互执行的计算器语言。
# 常见用法 echo "1.23*5" | bc 

#######数据库信息（将内存等信息写入数据库）##########
HOSTNAME="127.0.0.1" 
PORT="3306"
USERNAME="root"
PASSWORD="kang"
DBNAME="sunkang" 
TABLENAME="monitor"   #biao 
#################
#CPU使用情况
# top -n 参数指定运行次数，1代表运行一次即停止，不再等待top数据更新，使用awk指定分割符，提取数据
#cpu_us=`top -n 1 | grep 'Cpu(s)' | awk -F'[" "%]+' '{print $3}'`
#cpu_sy=`top -n 1 | grep 'Cpu(s)' | awk -F'[" "%]+' '{print $5}'`
#cpu_idle=`top -n 1 | grep 'Cpu(s)' | awk -F'[" "%]+' '{print $9}'` #闲置量
#top="$cpu_us+$cpu_sy"|bc  #使用量
which sar > /dev/null 2>&1
if [ $? -ne 0 ]
then
  total=`vmstat 1 5|awk '{x+=$13;y+=$14}END{print x+y}'`
  average=$(echo "scale=2;$total/5"|bc)
fi
cpu=$average

#超过阀值即发送邮件
#if [ $cpu_sum -ge 90 ];then
#        echo "CPU utilization $cpu_sum" | mail -s "cpu status warning." arppinging@163.com
#fi


#内存使用情况
memery_total=$(free -m | awk 'NR==2' | awk '{print $2}') #总大小
memery_used=$(free -m | awk 'NR==2' | awk '{print $3}') #已占用使用（已使用=已占用使用-缓存区）
memery_free=$(free -m | awk 'NR==2' | awk '{print $4}') #空闲
memery_buff=$(free -m | awk 'NR==2' | awk '{print $6}') #缓存区，还未用

#磁盘使用情况(注意：需要用sed先进行格式化才能进行累加处理)
#不累加了
disk_used=$(df -m | awk 'NR==2' | awk '{print $3}')/1024
disk_totalSpace=$(df -m | awk 'NR==2' | awk '{print $2}')/1024

#disk_used=$(df -m | sed '1d;/ /!N;s/\n//;s/ \+/ /;' | awk '{used+=$3} END{print used}')/1024
#disk_totalSpace=$(df -m | sed '1d;/ /!N;s/\n//;s/ \+/ /;' | awk '{totalSpace+=$2} END{print totalSpace}')/1024



#带宽使用情况(通过出入字节数除时间)
rx_before=$(cat /proc/net/dev | grep 'eth0' | tr : " " | awk '{print $2}')
tx_before=$(cat /proc/net/dev | grep 'eth0' | tr : " " | awk '{print $10}')
sleep 2
rx_after=$(cat /proc/net/dev | grep 'eth0' | tr : " " | awk '{print $2}')
tx_after=$(cat /proc/net/dev | grep 'eth0' | tr : " " | awk '{print $10}')
In_Speed=$[(rx_after-rx_before)/1024/2] #进入的带宽
Out_Speed=$[(tx_after-tx_before)/1024/2] #出的带宽

insert_sql="insert into ${TABLENAME} values(null,$cpu,$memery_total,$memery_used,$memery_free,$memery_buff,$disk_used,$disk_totalSpace,$In_Speed,$Out_Speed,null)"
/usr/bin/mysql -h${HOSTNAME}  -P${PORT}  -u${USERNAME} -p${PASSWORD} ${DBNAME} -e  "${insert_sql}"
