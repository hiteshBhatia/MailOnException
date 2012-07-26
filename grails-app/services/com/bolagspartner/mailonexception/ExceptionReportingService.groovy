package com.bolagspartner.mailonexception

import javax.servlet.http.HttpServletRequest
import grails.util.GrailsUtil
import grails.util.Environment
import org.codehaus.groovy.grails.commons.ConfigurationHolder


class ExceptionReportingService {

    def asynchronousMailService

    def getConfig() {
        return ConfigurationHolder.config
    }

    void getExceptionMapAndSendMail(HttpServletRequest request) {
        sendExceptionMail(getExceptionMap(request))
    }

    private void sendExceptionMail(Map exceptionMap) {
        Map mailInfoMap = exceptionMap.mailInfoMap
        List keys = mailInfoMap.keySet().toList() - "Environment"
        try {
            asynchronousMailService.sendAsynchronousMail {
                to exceptionMap.to
                from exceptionMap.from
                subject exceptionMap.subject
                body(view: "/report/errorMail", model: [exceptionMap: exceptionMap, mailInfoMap: mailInfoMap, keys: keys])
            }
        } catch (Exception e) {
            println "Failed While Sending Mail"
            e.printStackTrace()
            log.debug(e.message)
        }
    }

    Map getExceptionMap(HttpServletRequest request) {
        Map exceptionMap = [:]
        try {
            Date exceptionTime = new Date()
            String subject = """[Exception On ${GrailsUtil.environment} At ${exceptionTime.format("yyyy-MM-dd hh:mm")}]-[${request.exception.cause.message}]"""
            exceptionMap.subject = subject.size() > 100 ? "${subject.substring(0, 100)} ]" : subject
            exceptionMap.exceptionTime = exceptionTime
            exceptionMap.mailInfoMap = populateInfo(request, exceptionTime)
            exceptionMap.from = config.grails.mail.default.from ?: config.mailOnException.default.from
            exceptionMap.to = config.mailOnException.defaultReceivers
            String stackTrace = """${request.exception?.stackTraceLines?.toString()}"""
            exceptionMap.stackTrace = stackTrace
        } catch (Exception e) {
            println "Failed While Preparing Exception Mail"
            log.debug(e.message)
        }
        return exceptionMap
    }

    private Map populateInfo(HttpServletRequest request, Date exceptionTime) {
        Map mailMap = [:]
        try {
            mailMap["Environment"] = Environment.currentEnvironment.name
            mailMap["Server Name"] = request.serverName
            mailMap["IP Address"] = request.remoteAddr
            mailMap["URI"] = request.getRequestURI()
            mailMap["Params "] = request.parameterMap
            mailMap["Client Information"] = request.getHeader("User-Agent")
            mailMap["Referer "] = request.getHeader("referer")
            mailMap["Method "] = request.method
            mailMap["Time "] = exceptionTime
        } catch (Exception e) {
            println "Failed When Populating Info"
            log.debug(e.message)
        }
        return mailMap
    }
}
