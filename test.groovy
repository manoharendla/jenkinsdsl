job('mano-1') {
    description 'Build and test the app.'
    configure { project -> 
       project / 'buildWrappers' / 'EnvInjectPasswordWrapper' {
         injectGlobalPasswords ('true')
         maskPasswordParameters ('true')           
            
        }
    }
    scm {
        git {
            remote {
                github('account/repo', 'https')
                credentials('mano-github')
                branch('*/main')
            }
        }
    }
    steps {
        shell '''
        echo "hello"
        python --version
        pip install -r requirements.txt
        '''
    }
    
}
