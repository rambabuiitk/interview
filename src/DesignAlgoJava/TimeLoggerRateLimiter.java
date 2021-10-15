package DesignAlgoJava;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TimeLoggerRateLimiter {

    public static void main(String[] args) {
        Logger logger = new Logger();
        System.out.println("shouldPrintMessage(1, foo): " + logger.shouldPrintMessage(1, "foo"));
        System.out.println("shouldPrintMessage(2, bar): " + logger.shouldPrintMessage(2, "bar"));
        System.out.println("shouldPrintMessage(3, foo): " + logger.shouldPrintMessage(3, "foo"));
        System.out.println("shouldPrintMessage(8, foo): " + logger.shouldPrintMessage(8, "bar"));
        System.out.println("shouldPrintMessage(10, foo): " + logger.shouldPrintMessage(10, "foo"));
        System.out.println("shouldPrintMessage(11, foo): " + logger.shouldPrintMessage(11, "foo"));

    }

    private static class Logger {
        private Queue<TimeMsg> msgQueue;
        private final int maxTimeWindow;

        /**
         * Initialize your data structure here.
         */
        public Logger() {
            maxTimeWindow = 10;
            msgQueue = new LinkedList<>();
        }

        /**
         * Returns true if the message should be printed in the given timestamp, otherwise returns false.
         * If this method returns false, the message will not be printed.
         * The timestamp is in seconds granularity.
         */
        boolean shouldPrintMessage(int timestamp, String message) {
            removeExpiredLogs(timestamp);
            Iterator<TimeMsg> iter = msgQueue.iterator();
            while (iter.hasNext()) {
                TimeMsg tm = iter.next();
                if (tm.msg.equals(message)) {
                    return false;
                }
            }
            msgQueue.offer(new TimeMsg(timestamp, message));
            return true;
        }

        // Remove all logs that are too old.
        private void removeExpiredLogs(int currTimeStamp) {
            // validate against the peek of the queue and delete all logs older than peek
            while (!msgQueue.isEmpty()
                    && msgQueue.peek().timestamp <= currTimeStamp - maxTimeWindow) {
                msgQueue.poll();
            }
        }

        private class TimeMsg {
            int timestamp;
            String msg;

            public TimeMsg(int timestamp, String msg) {
                this.timestamp = timestamp;
                this.msg = msg;
            }
        }
    }
}
