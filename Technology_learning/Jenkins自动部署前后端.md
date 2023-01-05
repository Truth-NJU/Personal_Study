参考：[https://learnku.com/articles/44764](https://learnku.com/articles/44764)
## 1. 安装JDK
## 2. 安装nginx
## 3. 安装Jenkins

1. 在 Ubuntu 上安装 Jenkins 相对比较直接。我们将会启用 Jenkins APT 软件源，导入源 GPGkey，并且安装 Jenkins 软件包。使用下面的wget命令，导入 Jenkins 软件源的 GPG keys： `wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add - `
2. 添加软件源到系统中：`sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'`
3. 一旦 Jenkins 软件源被启用，升级apt软件包列表，并且安装最新版本的 Jenkins：
   1. `sudo apt update`
   2. `sudo apt install jenkins`
      1. 注意Jenkins 2.357以上的版本会和jdk1.8产生冲突。参考：[Jenkins -- 踩坑记录之JDK8不兼容 – 学习的伊甸园](http://www.yiduoyun.space/archives/jenkins--%E8%B8%A9%E5%9D%91%E8%AE%B0%E5%BD%95%E4%B9%8Bjdk8%E4%B8%8D%E5%85%BC%E5%AE%B9)
      2. 查询Jenkins可安装版本：`apt-cache madison jenkins`
      3. 安装指定版本Jenkins：`apt-get install -y jenkins=x.xxx.x`
5. 在安装完成后，Jenkins 服务将会被自动启动。你可以通过打印服务状态来验证它：`systemctl status jenkins`。你应该看到类似下面的信息：
> jenkins.service - Jenkins Continuous Integration Server
     Loaded: loaded (/lib/systemd/system/jenkins.service; enabled; vendor prese>
     Active: activating (start) since Mon 2023-01-02 15:00:19 CST; 1min 2s ago
   ……

6. 输入服务器ip地址加上端口 8080，`http://your_ip_or_domain:8080`。等待Jenkins开启后会展示该页面。![截屏2023-01-02 下午3.12.49.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672643573904-b4f21557-cba6-40f1-9e52-28c84b469d92.png#averageHue=%23f3f3f3&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=433&id=u3c923712&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.12.49.png&originHeight=1462&originWidth=2304&originalType=binary&ratio=1&rotation=0&showTitle=false&size=332373&status=done&style=none&taskId=uee1eb188-e228-4b25-8806-5d94d7ce50c&title=&width=683)
7. 使用`sudo cat /var/lib/jenkins/secrets/initialAdminPassword`命令获得本地的密码，粘贴到页面中。
8. 点击安装推荐的插件进行安装。![截屏2023-01-02 下午3.16.30.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672643792972-469ba342-b0b4-452e-9441-0a2553d3c15e.png#averageHue=%23f1f1f1&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=365&id=u1f1db7ad&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.16.30.png&originHeight=1462&originWidth=2304&originalType=binary&ratio=1&rotation=0&showTitle=false&size=393550&status=done&style=none&taskId=ub263cef8-e3d0-41ab-9d1e-0d275f0d5dd&title=&width=575)
9. 按照页面提示创建一个管理员账户。![截屏2023-01-02 下午3.21.36.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672644100035-a385f975-e166-4f03-b43a-4501bdd9361b.png#averageHue=%23f5f4f4&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=386&id=ud9c6fa2f&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.21.36.png&originHeight=1462&originWidth=2304&originalType=binary&ratio=1&rotation=0&showTitle=false&size=217988&status=done&style=none&taskId=u04ec0eef-5a91-4ed4-bacb-2b97c9135e6&title=&width=608)
10. 设置 Jenkins 实例的 URL 地址。这个文本域将会被自动填写生成的 URL。![截屏2023-01-02 下午3.22.37.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672644161176-af1460bb-bc39-4a03-aadf-2f31fb11c7b2.png#averageHue=%23f4f4f4&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=394&id=ucc76032f&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.22.37.png&originHeight=1462&originWidth=2304&originalType=binary&ratio=1&rotation=0&showTitle=false&size=276253&status=done&style=none&taskId=u24d32caa-6e33-444f-b067-fff03bbabe9&title=&width=621)
11. 点击开始使用jenkins，重定向到jenkins工作台。安装jenkins完成
## 4. github 生成 Personal Access Token

1. github –> 头像 –> Settings –> Developer settings –> Personal access tokens –> Generate new token
2. 勾选如下图所示，最后点击 generate token 生成令牌即可。![截屏2023-01-02 下午3.29.15.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672644559199-a2b6e8ed-f930-4e4b-9984-e515ded4b0fe.png#averageHue=%23fefefe&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=515&id=uf06ab3d8&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.29.15.png&originHeight=1352&originWidth=1540&originalType=binary&ratio=1&rotation=0&showTitle=false&size=308168&status=done&style=none&taskId=u4c951da0-26a2-4fd4-b508-b06fa6ab142&title=&width=587)
3. 生成令牌之后要记录下来，因为只显示一次。
## 5. github 设置 GitHub webhooks
在具体需要持续集成的项目下进行操作

1. 现有项目->settings->Webhooks
2. 选项url：部署的服务器的 IP + 端口 + github-webhook
3. 点击最下方的Add Webhook按钮![截屏2023-01-02 下午3.36.18.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672644983873-e7ae457b-ecee-46c7-b039-ed24d7d63b97.png#averageHue=%23fefefe&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u6fe188c4&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.36.18.png&originHeight=1372&originWidth=2434&originalType=binary&ratio=1&rotation=0&showTitle=false&size=340165&status=done&style=none&taskId=u269fe476-1a56-4dff-bf1d-85dd6041c1c&title=)
## 6. 设置 jenkins 的 github 配置

1. jenkins 点击create a job创建一个新任务，填写你的任务名称，并选择构建freestyle project![截屏2023-01-02 下午3.39.37.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645181569-9b337d52-836c-48da-baaf-2f1938f73a59.png#averageHue=%23c8a98c&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=361&id=u4d5f9b80&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.39.37.png&originHeight=1372&originWidth=2434&originalType=binary&ratio=1&rotation=0&showTitle=false&size=198830&status=done&style=none&taskId=u7139aec8-d2bc-450f-8138-0580d6c9b1c&title=&width=640)![截屏2023-01-02 下午3.41.06.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645291729-eb0e2169-f6ed-491d-996c-1a23248d0322.png#averageHue=%23e8e4d8&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=319&id=u417637e1&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.41.06.png&originHeight=1394&originWidth=2794&originalType=binary&ratio=1&rotation=0&showTitle=false&size=453372&status=done&style=none&taskId=u25cbb479-b981-4737-a0e0-ed55f20c6ee&title=&width=640)
2. Manage Jenkins –> Configure System
3. 找到 GitHub 选项 –> 添加 Github 服务器 –> GitHub Server![截屏2023-01-02 下午3.43.58.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645441885-c803d6c8-25aa-40cc-999f-5c889d4a7238.png#averageHue=%23fefefe&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=286&id=u5af445d9&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.43.58.png&originHeight=1394&originWidth=2794&originalType=binary&ratio=1&rotation=0&showTitle=false&size=236360&status=done&style=none&taskId=u9c004009-0bf4-45f6-9f6a-82b87f40fd3&title=&width=574)
4. 勾选 “管理 Hook”，添加 –> Jenkins![截屏2023-01-02 下午3.45.00.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645504277-5b678f9b-3640-4903-b18a-58878739c5bc.png#averageHue=%23fefefe&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=308&id=u3d419fc5&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.45.00.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=210591&status=done&style=none&taskId=udcabbc34-8b45-4a6f-9edc-9a1d8db769e&title=&width=645)
5. 在弹出的窗口中，如下图配置，这里需要用到之前生成的令牌![截屏2023-01-02 下午3.46.17.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645580066-2564781b-ba7a-4525-8cf5-5c51b1f3a185.png#averageHue=%23e8e8e8&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=307&id=u2ba524ee&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.46.17.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=202018&status=done&style=none&taskId=u6a4450be-9143-4384-90d3-5944ac97a82&title=&width=643)
6. 选择生成的凭证，测试 jenkins 连接 github 服务器，如下图所示则配置成功，记得在页面底部保存配置。![截屏2023-01-02 下午3.47.46.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645719970-38589729-0f65-4857-873c-c65d4e760525.png#averageHue=%23fefefe&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=319&id=u3adc17f1&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.47.46.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=183795&status=done&style=none&taskId=uba5dc5f9-7adc-4ffa-9622-8b45f7d6973&title=&width=667)
## 7. 设置 jenkins 的项目配置
### 7.1 进入该项目的配置项
### ![截屏2023-01-02 下午3.50.36.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645840596-c44a016f-7554-4092-ab30-97ec9f367ce6.png#averageHue=%23dcc29a&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=298&id=udc71ae06&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.50.36.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=278470&status=done&style=none&taskId=u3dc0dcd2-c415-46a0-adec-575058d5a4f&title=&width=623)
### 7.2 选择 github 项目，并填入项目 URL（复制浏览器上的地址即可）
### ![截屏2023-01-02 下午3.53.03.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672645986978-e0584167-a494-480d-b524-83b9723cb1d4.png#averageHue=%23fefefe&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ud3e73d2c&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.53.03.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=243558&status=done&style=none&taskId=u7282f17a-92ef-4996-a3aa-91c5f0e4d54&title=)
### 7.3 在源码管理下选择 git，并输入 Repository URL（克隆下载你项目的地址）
### ![截屏2023-01-02 下午3.54.21.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672646064971-33f867e9-418d-4be3-9f45-5b39647972c5.png#averageHue=%23fefdfd&clientId=ueac928e7-d5af-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u4ea2786b&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%883.54.21.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=289079&status=done&style=none&taskId=uaa81e59f-f99f-46b1-abd8-437fe12b27f&title=)

1. 若没有安装git
   1. `sudo apt update`
   2. `sudo apt install git`
   3. `git --version`
2. 安装完git后，仍然会报上面的错误，在服务器上切换到jenkins用户`su jenkins`，执行报错中类似于`git ls-remote -h git@bitbucket.org:person/projectmarket.git HEAD`形式的命令。首次通过 SSH 连接到新主机时，您将收到标准 SSH 警告：`The authenticity of host 'bitbucket.org (207.223.240.181)' can't be established. RSA key fingerprint is 97:8c:1b:f2:6f:14:6b:5c:3b:ec:aa:46:46:74:7c:40. Are you sure you want to continue connecting (yes/no)?` 键入yes并按 Enter。主机密钥github.com将会添加到~/.ssh/known_hosts文件中。
3. 这时手动在服务器的命令行执行`git ls-remote -h git@bitbucket.org:person/projectmarket.git HEAD`，还会出现如下报错：
```bash
git@github.com: Permission denied (publickey).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
```

4. 输入`ssh-keygen -t rsa -C "email of github"`命令生成id_rsa和id_rsa.pub文件，将id_rsa.pub中的内容复制，进入github账号，在settings下，SSH and GPG keys下new SSH key，title随便取一个名字，然后将id_rsa.pub里的内容复制到Key中，完成后Add SSH Key。（也就是配置一个免密clone）
5. 刷新页面重新填入url，报错消失。（注意2、3、4三步都是在jenkins用户下进行的）
6. 选择 Credentials，若下拉选项中有，则直接选择即可。若没有，点击添加 –> jenkins，添加一个 Username with passwrod 类型的权限用户，直接用 github 的登陆名称和密码创建。![截屏2023-01-02 下午4.36.53.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672648617001-55d07b8e-0789-41e4-91b5-6b3f16cfed0f.png#averageHue=%23e8e7e7&clientId=ub69e104b-c1e3-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u2a743bbc&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%884.36.53.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=250092&status=done&style=none&taskId=u6463072a-750c-4671-b906-a43491bcb27&title=)
7. 最终源码管理的配置类似于下图![image.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672648779153-a2b9fb1b-753a-4719-8ca7-739a9560ff47.png#averageHue=%23f6f4f4&clientId=ub69e104b-c1e3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=359&id=u385ffb4a&margin=%5Bobject%20Object%5D&name=image.png&originHeight=820&originWidth=1680&originalType=binary&ratio=1&rotation=0&showTitle=false&size=129583&status=done&style=none&taskId=u088c6b4d-b6cf-413f-91db-b6c38dd070f&title=&width=735)
### 7.4 构建触发器 + 构建环境 + 绑定配置
点击新增并选择 secret text 选项，在新出现的选项中选择添加的权限用户
![image.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672649240977-c9fd7fce-1e8a-4d08-8999-65958c73895d.png#averageHue=%23f9f8f8&clientId=ub69e104b-c1e3-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=410&id=u965dfdc7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=820&originWidth=1680&originalType=binary&ratio=1&rotation=0&showTitle=false&size=108892&status=done&style=none&taskId=u8f508ca9-9973-432f-ace1-3d64ad52135&title=&width=840)
### 7.5 构建配置，添加执行 shell
![截屏2023-01-02 下午4.48.27.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672649310626-a4cc6195-dcb5-4e3f-8e04-3b42226a5152.png#averageHue=%23fefefe&clientId=ub69e104b-c1e3-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=309&id=u58681a0e&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%884.48.27.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=267395&status=done&style=none&taskId=u8a81c305-3812-4198-be8e-a399d7d9242&title=&width=647)
既然可以执行 shell 命令，可以先来执行 pwd，看下默认的工作目录是在哪里。
![截屏2023-01-02 下午4.49.37.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672649380933-7670384f-aba3-4764-80cf-bb4f8f5540f2.png#averageHue=%23fefefe&clientId=ub69e104b-c1e3-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=329&id=u8675655d&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%884.49.37.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=254918&status=done&style=none&taskId=u18be9284-81a6-4e26-ae38-16c7011b6f4&title=&width=688)
保存后，点击立即构建，就会在 build history 下面看到本次构建的 ID。可以点击查看控制台输出。![截屏2023-01-02 下午5.54.48.png](https://cdn.nlark.com/yuque/0/2023/png/25666990/1672653293629-f18469d7-8a3a-4a42-8d5f-6588809fa381.png#averageHue=%23f6f6f6&clientId=uad4c3915-b826-4&crop=0&crop=0&crop=1&crop=1&from=drop&height=336&id=u02ac5aab&margin=%5Bobject%20Object%5D&name=%E6%88%AA%E5%B1%8F2023-01-02%20%E4%B8%8B%E5%8D%885.54.48.png&originHeight=1346&originWidth=2816&originalType=binary&ratio=1&rotation=0&showTitle=false&size=452394&status=done&style=none&taskId=u5d5b0f24-e7f9-4bf0-b749-86f8df4f569&title=&width=703)
## 8. 编写shell命令
### 8.1 前端
```bash
npm install
# 将前端打包成dist
npm run build


# 下面命令需要在服务器上手动执行
docker ps
docker stop container_id
docker container rm container_id
# dist文件夹必须是绝对路径
# 这一步之前需要确保jenkins用户可以执行sudo命令，不可以执行的话可以在root用户下使用echo 'jenkins ALL=(ALL) ALL' >> /etc/sudoers命令进行添加权限
echo "Smtc123456" | sudo -S docker run -p 8081:80 -d -v /var/lib/jenkins/workspace/NH_Frontend/dist:/usr/share/nginx/html nginx
```
### 8.2 后端
```bash
mvn clean package
mvn package -B -Dmaven.test.skip=true
netstat -tunlp|grep 8082|awk '{print $7}'|cut -d '/' -f 1|xargs test -z || netstat -tunlp|grep 8082|awk '{print $7}'|cut -d '/' -f 1|xargs kill
nohup java -jar /var/lib/jenkins/workspace/NH_Backend/target/backend-NH-0.0.1-SNAPSHOT.jar --server.port=8082 >/dev/null 2>&1 &
```
