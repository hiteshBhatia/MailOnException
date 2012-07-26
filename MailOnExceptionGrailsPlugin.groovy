import org.codehaus.groovy.grails.commons.ConfigurationHolder
import grails.util.GrailsUtil

class MailOnExceptionGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.4 > *"
    // the other plugins this plugin depends on
    def dependsOn = ['asynchronousMail': '0.1.0 > *']

    def loadAfter = ['asynchronousMail'];
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Hitesh Bhatia"
    def authorEmail = "hitesh@intelligrape.com"
    def title = "Sends mail on Exception"
    def description = '''Sends mail on Exception'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/mail-on-exception"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        def config = ConfigurationHolder.config
        GroovyClassLoader classLoader = new GroovyClassLoader(getClass().classLoader)
        // merging default config into main application config
        try {
            config.merge(new ConfigSlurper(GrailsUtil.environment).parse(classLoader.loadClass('DefaultMailOnExceptionConfig')))
        } catch (Exception ignored) {
            // ignore, just use the defaults
        }
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
