package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.LogoutRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.TokenResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationServiceInterface {
    TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException;
    void logout(LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException;
    AuthenticationResponse login(UserRequest userRequest) throws JOSEException, AppException;
    AuthenticationResponse signup(UserRequest userRequest) throws JOSEException, AppException;
    AuthenticationResponse update(UserRequest userRequest) throws JOSEException, AppException;
}
