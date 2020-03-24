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

          stage('k8s部署'){
                                def deploy_image_name="39.108.190.246/library/itoken-eurke:latest"
                                sh "cd ./Itoken"
                                sh "ls"
                                sh """
                                   sed -i 's#\$IMAGE_NAME#${deploy_image_name}#' ${project_name}/deploy.yml
                                   sed -i  's#\$SECRET_NAME#${secret_name}#' ${project_name}/deploy.yml

                                """
                                kubernetesDeploy configs:"${project_name}/deploy.yml",kubeconfigId:"${k8s_auth}"

                        }






}
