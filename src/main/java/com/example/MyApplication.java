package com.example;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS をロードするためのクラスです。
 */
@ApplicationPath("/")
public class MyApplication extends Application {
  // GlassFish ではオーバーライドは不要
}