package com.darly.snbc.jarlib.common.bolts.tasks.exception;

/**
 * This is a wrapper class for emphasizing that task failed due to bad {@code Executor}, rather than
 * the continuation block it self.
 */
public class ExecutorException extends RuntimeException {

  public ExecutorException(Exception e) {
    super("An exception was thrown by an Executor", e);
  }
}
