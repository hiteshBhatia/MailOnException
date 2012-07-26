class MailOnExceptionUrlMappings {
    static mappings = {
        "500"(controller: "report", action: "error")
    }
}
