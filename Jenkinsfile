import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

node {
	properties([disableConcurrentBuilds()])
	def shLib = new es.vged.jenkins.ShdLib()
	def microservice = "thefarmerkit-backend"
	def namespace = "thehillogy"
	def config = "thefarmerkit-ciconfig"
	//additional receipients
	def emailList = "sgutierrez@thehillogy.com"
	def nameApi = "thefarmerkit-backend"
	try {
		shLib.checkout(config, namespace)
		shLib.buildMvn()
		shLib.sonarQubeAnalysis(emailList)
		
	} catch (e) { 
		currentBuild.result = "FAILED"
		shLib.notifyBuildStatus(currentBuild.result, emailList)
		throw e
	}	
	
	try {
		shLib.deployToCluster(env.BRANCH_NAME, config, microservice, namespace)
		
		try {
			echo "Trying to Update API Manager"
			shLib.createOrUpdateAPI(microservice,namespace,nameApi)
		} catch (e) { 
			echo "Exception thrown trying to update api manager." + e.toString()
		}
		
	} catch (e) {
		currentBuild.result = "FAILED"
		throw e
	} finally {
		shLib.notifyBuildStatus(currentBuild.result, emailList)
	}
}
