//package com.similiz.dictionary.aspect;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.similiz.dictionary.log.LogFilter;
//import com.similiz.dictionary.log.LogFormatter;
//import com.similiz.dictionary.log.LogHandler;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.logging.*;
//
//@Component
//@Aspect
//public class LoggingAspect {
//
//    Logger logger = Logger.getLogger(LoggingAspect.class.getName());
//
//    @Around("PublicPointCuts.allRepositoryMethods()")
//    public Object loggingAllRepositoryMethodsAdvice(ProceedingJoinPoint pjp)
//            throws Throwable {
//        Object object = null;
//        try {
//            LogManager.getLogManager().readConfiguration(Files.newInputStream(Paths.get("logging.properties")));
//        } catch (SecurityException | IOException e1) {
//            e1.printStackTrace();
//        }
//        logger.setUseParentHandlers(false);
//        logger.setLevel(Level.FINE);
////      logger.addHandler(new ConsoleHandler());
//        //adding custom handler
//        logger.addHandler(new LogHandler());
//        try {
//            //FileHandler file name with max size and number of log files limit
//            Handler fileHandler = new FileHandler("logger.log", 2000, 5);
//            fileHandler.setFormatter(new LogFormatter());
//            //setting custom filter for FileHandler
//            fileHandler.setFilter(new LogFilter());
//            logger.addHandler(fileHandler);
//
//            String methodName = pjp.getSignature().getName();
//            String className = pjp.getTarget().getClass().toString();
//            Object[] methodArgs = pjp.getArgs();
//
//            ObjectMapper mapper = new ObjectMapper();
//            logger.log(Level.INFO, "Method invoked " + className
//                    + " : " + methodName + "(), arguments : "
//                    + mapper.writeValueAsString(methodArgs));
//
//            long beforeMillis = System.currentTimeMillis();
//            object = pjp.proceed();
//            long afterMillis = System.currentTimeMillis();
//
//            logger.log(Level.INFO, className + " : " + methodName
//                    + "() worked for " + (afterMillis - beforeMillis)
//                    + "ms, response : " + mapper.writeValueAsString(object));
//        } catch (SecurityException | IOException e) {
//            e.printStackTrace();
//        }
//
//        return object;
//    }
//}
