package com.similiz.dictionary.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PublicPointCuts {
    @Pointcut(value = "execution(* com.similiz.dictionary.repository.*.*(..))")
    public void allRepositoryMethods() {
    }
}
