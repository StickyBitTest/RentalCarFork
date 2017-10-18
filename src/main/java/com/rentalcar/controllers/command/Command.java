package com.rentalcar.controllers.command;

import com.rentalcar.controllers.utils.SessionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseAttributes common interface for site commands
 */
public interface Command extends SessionContext {

    String execute(HttpServletRequest request, HttpServletResponse response);
}
