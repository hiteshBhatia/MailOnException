package com.bolagspartner.mailonexception

class ReportController {
    def exceptionReportingService

    def error = {
        try {
            exceptionReportingService.getExceptionMapAndSendMail(request)
        } catch (Exception e) {
            println "WARNING!!! : This is Extremely Bad, Failed While Reporting Error"
            log.debug(e.message)
        }
        render(view: "/error")
    }
}
