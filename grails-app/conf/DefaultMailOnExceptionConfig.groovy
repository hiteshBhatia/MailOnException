mailOnException.reportException = true
mailOnException.default.from = "mail@exception.com"

environments {
    production {
        mailOnException.defaultReceivers = ["karl-johan@bolagspartner.se", "hitesh@intelligrape.com", "sachin.verma@intelligrape.com", "avinash@intelligrape.com"]
    }
    test {
        mailOnException.defaultReceivers = ["karl-johan@bolagspartner.se", "hitesh@intelligrape.com"]
    }
    development {
        mailOnException.defaultReceivers = ["hitesh@intelligrape.com"]
    }
}
