### timezone
```sh
# timedatectl
# timedatectl set-timezone Asia/Seoul
```

### Sync Server Time
```
# yum -y install chrony
# timedatectl set-timezone Asia/Seoul
# vi /etc/chrony.conf		(타임서버 추가)
# systemctl restart chronyd
# systemctl status chronyd
# chronyc sources -v
```  
