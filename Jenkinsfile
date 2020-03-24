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
//k8s的凭证id
def k8s_auth="154b9c02-0591-4312-9049-8f66c31cf559"
//定义k8s-harbord的凭证
def secret_name = "registry-auth-secret"
node {





        stage('拉取代码'){
                    checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "${git_url}"]]])

            }

        stage('安装jar包'){

                            echo "开始安装jar包"
                            sh "ls"

                            sh "mvn -f ./Itoken/${project_name}     package "
                    }
        stage('制作镜像'){

                            echo "开始制作镜像"



                            sh "mvn -f ./Itoken/${project_name}  dockerfile:build"
                    }



        stage('镜像上传'){

                                echo "镜像打标签"

                                //定义镜像名称
                                def imageName = "${project_name}:${tag}"

                                //镜像打标签
                                sh "docker tag ${imageName} ${harbor_url}/${harbor_project}/${imageName}"

                                sh "docker login -u admin -p 123456 ${harbor_url}"
                                sh "docker push ${harbor_url}/${harbor_project}/${imageName}"
                                sh "echo 镜像上传成功"
                        }

        stage('镜像发布'){

                                    echo "开始发布镜像"
                                    sshPublisher(publishers: [sshPublisherDesc(configName: 'zcm 101.200.91.110', transfers: [sshTransfer(cleanRemote: false, excludes: '',execCommand: "/root/shell/deploy.sh $harbor_url $harbor_project $project_name $tag $port",execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])

                            }

        stage('k8s-本地修改文件'){
                    def deploy_image_name="39.108.190.246/library/itoken-eurke:latest"
                    sh "cd ./Itoken"
                    sh "ls"
                    sh "ls ./Itoken/${project_name}"
                    sh """
                       sed -i 's#\$IMAGE_NAME#${deploy_image_name}#' ./Itoken/${project_name}/deploy.yml
                       sed -i  's#\$SECRET_NAME#${secret_name}#' ./Itoken/${project_name}/deploy.yml

                    """

                    //sshPublisher(publishers: [sshPublisherDesc(configName: 'k8s', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'ls', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '/bin/bash')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                   // sh label: '', script: 'sh ./Itoken/${project_name}/k8s.sh '
                    //sh "ssh 101.200.91.110 ls"
                    sh "cat ./Itoken/${project_name}/deploy.yml"
                    //sh "ssh  106.13.114.80 "
                    //sh " ssh  106.13.114.80 ls /"

                   // sh " scp -p  ./Itoken/${project_name}/deploy.yml  106.13.114.80:/root/jenkins/Itoken/${project_name}"
                    //sh " ssh  106.13.114.80 mkdir -p /root/jenkins/Itoken/${project_name}"
                    //sh " ssh  106.13.114.80 kubectl apply -f  /root/jenkins/Itoken/${project_name}/deploy.yml"
                    //kubernetesDeploy configs:"Itoken/${project_name}/deploy.yml",kubeconfigId:"${k8s_auth}"

             }

        stage('k8s-远程创建文件'){
                sshPublisher(publishers: [sshPublisherDesc(configName: 'k8s', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''mkdir -p /root/jenkins/Itoken/${project_name}

                scp 120.26.38.228:/var/lib/jenkins/workspace/jenkins/Itoken/${project_name}/deploy.yml  /root/jenkins/Itoken/${project_name}/deploy.yml

                kubectl apply -f  /root/jenkins/Itoken/${project_name}/deploy.yml
                 ''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '/bin/bash')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])

          }






}
