package com.example.market.App.addInfoUtil;

import com.example.market.App.dtos.MailDtos.SendingMailDto;

import java.io.IOException;

public interface AddMailInfo {

    SendingMailDto mailInfo() throws IOException;
}
