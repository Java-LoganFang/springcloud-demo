//git凭证id
def git_auth = ""
//git的Url
def git_url = "https://gitee.com/java_learning2/springcloud.git"
//docker镜像的版本号
def tag = "latest"
//docker私服Harbor地址
def harbor_url = "39.108.190.246"
//上传到的仓库名称
def harbor_project = "library"
node {
    stage('拉取代码'){
            checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "${git_url}"]]])

    }

    stage('安装jar包'){

                    echo "开始安装jar包"
                    sh "ls"

                    sh "mvn -f ./Itoken/${project_name}   clean  package "
            }
    stage('制作镜像'){

                        echo "开始制作镜像"


                        sh "mvn -f ./Itoken/${project_name}  dockerfile:build"
                }



    stage('镜像上传'){

                            echo "镜像打标签"

                            //定义镜像名称
                            def imageName = "${project.artifactId}:${tag}"

                            //镜像打标签
                            sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"

                            sh "docker login -u admin -p 123456 ${harbor_url}"
                            sh "docker push ${harbor_url}/${harbor_project}/${imageName}"
                            sh "echo 镜像上传成功"
                    }
}
