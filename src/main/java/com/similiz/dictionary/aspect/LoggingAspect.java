//package com.similiz.dictionary.aspect;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.similiz.dictionary.log.LogFilter;
//import com.similiz.dictionary.log.LogFormatter;
//import com.similiz.dictionary.log.LogHandler;
//import com.similiz.dictionary.util.PropertiesUtil;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
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
//        try (InputStream in = getClass().getResourceAsStream("/logging.properties")) {
//            LogManager.getLogManager().readConfiguration(in);
//        } catch (SecurityException | IOException e1) {
//            e1.printStackTrace();
//        }
//        logger.setUseParentHandlers(false);
//        logger.setLevel(Level.FINE);
//        //  adding custom handler
//        logger.addHandler(new LogHandler());
//        try {
//            //  get absolute path to logger
//            URL resource = getClass().getClassLoader().getResource(PropertiesUtil.get("aspect.logging.file"));
//            assert resource != null;
//            String absolutePath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
//            //  FileHandler file name with max size and number of log files limit
//            Handler fileHandler = new FileHandler(absolutePath, true);
//            fileHandler.setFormatter(new LogFormatter());
//            fileHandler.setFilter(new LogFilter());
//            logger.addHandler(fileHandler);
//
//            String methodName = pjp.getSignature().getName();
//            String className = pjp.getTarget().getClass().toString();
//            Object[] methodArgs = pjp.getArgs();
//
//            ObjectMapper mapper = new ObjectMapper();
//            logger.log(Level.INFO, "\nMethod invoked " + className
//                    + " : " + methodName + "(), arguments : "
//                    + mapper.writeValueAsString(methodArgs));
//
//            long beforeMillis = System.currentTimeMillis();
//            object = pjp.proceed();
//            long afterMillis = System.currentTimeMillis();
//
//            logger.log(Level.INFO, "\n" + className + " : " + methodName
//                    + "() worked for " + (afterMillis - beforeMillis)
//                    + "ms, response : " + mapper.writeValueAsString(object));
//
//            fileHandler.close();
//        } catch (SecurityException | IOException e) {
//            e.printStackTrace();
//        }
//
//        return object;
//    }
//}
