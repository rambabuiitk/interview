public class TokenBucketFilter {

    private long possibleTokens;

    private final int MAX_TOKENS;
    private final int ONE_SECOND = 1000;

    public TokenBucketFilter(int maxTokens) {
        MAX_TOKENS = maxTokens;

        Thread dt = new Thread(() -> {
            daemonThread();
        });
        dt.setDaemon(true);
        dt.start();
    }

    private void daemonThread() {
        while (true) {
            synchronized (this) {
                if (possibleTokens < MAX_TOKENS) {
                    possibleTokens++;
                }
                this.notify();
            }

            try {
                Thread.sleep(ONE_SECOND);
            } catch (InterruptedException e) {

            }
        }
    }

    public void getToken() throws InterruptedException {
        synchronized (this) {
            while (possibleTokens == 0) {
                this.wait();
            }

            possibleTokens--;
        }

        System.out.println("Granting token");
    }

    public static void main(String[] args) {
        TokenBucketFilter tf = new TokenBucketFilter(10);
    }


}
