def git_auth = ""
def git_url = "https://gitee.com/java_learning2/springcloud.git"
node {
    stage('拉取代码'){
            checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: "${git_url}"]]])

    }

    stage('安装jar包'){

                    echo "开始安装jar包"
                    sh "ls"
                    sh "mvn -f ./Itoken/${project_name}   clean  package"
            }
}
