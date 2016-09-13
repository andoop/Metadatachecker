package com.viey.demo;


public class Main
{
  public static void main(String[] args)
  {
    System.out.println("============Code by andoop =============");
    System.out.println(">>>>>>>>meta-data查看工具<<<<<<<<<");

    if (args.length != 1) {
      System.out.println("==ERROR==usage:java -jar check.jar apkDirectory======");
      return;
    }

    String apk = args[0];
    Checker checker = new Checker(apk);
    checker.check();
  }
}