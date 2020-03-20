def git_auth = ""
def git_url = "https://gitee.com/java_learning2/springcloud.git"
node {
    stage('拉取代码'){
            checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "${git_url}"]]])

    }

    stage('安装公共工程'){
                //sh "mvn -f hello-spring-cloud-dependencies clean install"
                echo "省略安装公共打包"
        }

     stage('安装公共工程'){
               sh "mvn -f ${project_name} clean package"

         }


}
