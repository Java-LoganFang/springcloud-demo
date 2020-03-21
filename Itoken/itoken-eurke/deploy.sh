#!/bin/sh
#接收外部参数
harbor_url=$1
harbor_project_name=$2
project_name=$3
tag=$4
port=$5

imageName=$harbor_url/$harbor_project_name/$project_name:$tag

echo "$imageName"

#查询容器是否存在，存在就删除
containerId=$(docker ps -a | gerp -w ${project_name}:${tag} | awk '{print $1}')

if [ "$containerId" != "" ]; then
  #停掉容器
  docker stop $containerId

  #删除容器
  docker rm $containerId
  echo "容器删除成功"

fi
#查询容器是否存在，存在就删除
imageId=$(docker images | grep -w $project_name | awk '{print $3}')
if [ "$imageId" != "" ]; then
  #删除镜像
  docker rmi -f $imageId
  echo "成功删除镜像"
fi

#登录Harbor
docker login -u admin -p 123456 $harbor_url
echo "登录成功"

#下载镜像
docker pull $imageName
echo "下载成功"

#启动容器
docker run -di -p $port:$port $imageName

echo "容器启动成功"
