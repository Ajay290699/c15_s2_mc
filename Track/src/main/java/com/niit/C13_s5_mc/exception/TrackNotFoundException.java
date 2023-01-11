package com.niit.C13_s5_mc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Track not found")
public class TrackNotFoundException extends Exception{
}
