package com.arawakanda.springmultiplesmtpmailserver.payload;

import lombok.Data;

@Data
public class SendEmailData {

    private String protocol = "smtp";

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String fromAddress;

    private String toEmail;

    private boolean tlsEnabled = true;

    private boolean sslEnabled = false;

    private boolean authEnabled = true;

}
