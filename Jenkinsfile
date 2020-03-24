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

        stage('k8s部署'){
                    def deploy_image_name="39.108.190.246/library/itoken-eurke:latest"
                    sh "cd ./Itoken"
                    sh "ls"
                    sh "ls ./Itoken/${project_name}"
                    sh """
                       sed -i 's#\$IMAGE_NAME#${deploy_image_name}#' ./Itoken/${project_name}/deploy.yml
                       sed -i  's#\$SECRET_NAME#${secret_name}#' ./Itoken/${project_name}/deploy.yml

                    """
                    sh label: '', script: 'sh ./Itoken/${project_name}/k8s.sh '
                    //sh "ping 106.13.114.80"
                    //sh "cat ./Itoken/${project_name}/deploy.yml"
                    //sh "ssh  106.13.114.80 "
                    //sh " ssh  106.13.114.80 ls /"
                    //sh " ssh  106.13.114.80 mkdir -p /root/jenkins/Itoken/${project_name}"
                    //sh " scp -p  ./Itoken/${project_name}/deploy.yml  106.13.114.80:/root/jenkins/Itoken/${project_name}"
                    //sh " ssh  106.13.114.80 kubectl apply -f  /root/jenkins/Itoken/${project_name}/deploy.yml"
                    //kubernetesDeploy configs:"Itoken/${project_name}/deploy.yml",kubeconfigId:"${k8s_auth}"

             }






}
