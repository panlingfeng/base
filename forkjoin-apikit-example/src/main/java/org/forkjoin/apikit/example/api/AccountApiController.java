package org.forkjoin.apikit.example.api;

import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.example.model.User;
import org.forkjoin.apikit.example.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "v1")
public class AccountApiController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "account/login", method = RequestMethod.POST)
    public Result<String> login() throws Exception {
        String token = accountService.login();
        return Result.createSuccess(token);
    }

    /**
     * 测试需要登录
     */
    @RequestMapping(value = "account/testLogin", method = {RequestMethod.POST})
    public Result<Void> testLogin() throws Exception {
        return Result.createSuccess();
    }

    /**
     * 测试不需要登录
     */
    @Account(false)
    @RequestMapping(value = "baseUrl/testNotLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<User> testNotLogin(@Valid User user, Object accountName) throws Exception {
        return Result.createSuccess();
    }
}