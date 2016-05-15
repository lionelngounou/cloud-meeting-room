<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="container">
        <div class="navbar-header">
            <a href="${indexURL}" class="navbar-brand">
              <b>C</b>loud<b>M</b>eeting<b>R</b>oom<b class=" disabled">.com</b>
            </a>
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
        </div>
        
        <div class="navbar-collapse collapse" id="navbar-main">
            <ul class="nav navbar-nav">
                <li class="${generalModel.section=='bookings'?'active':''}">
                    <a href="${bookingURL}" class="navbar-btn">Bookings</a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a class="navbar-btn " href='${loginURL}' >Login</a></li>
                <li>
                    <a class="navbar-btn" id="navbar-reg-btn" href='${registerURL}' >
                        Register
                    </a>
                </li>
            </ul>

        </div>
    </div>
