
import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;

import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

public class EmbeddedGlassFish {
    public static void main(String... args) {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        /* システムプロパティから設定値取得 */
        Properties props = System.getProperties();
        int port = Integer.valueOf(props.getProperty("PORT"));

        String listener = "";
        logger.info("set http-listener.");
        listener = "http-listener";

        try {
            GlassFishProperties glassfishProperties = new GlassFishProperties();
            logger.info("setPort listener=" + listener);
            logger.info("setPort port=" + port);
            glassfishProperties.setPort(listener, port);

            // 埋め込みサーバーの初期化
            // シャットダウン・フックから呼び出すためfinal宣言します
            final GlassFish glassfish = GlassFishRuntime.bootstrap()
                    .newGlassFish(glassfishProperties);
            // シャットダウン・フック登録
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        glassfish.stop();
                        glassfish.dispose();
                        System.err.println("GlassFish を停止しました");
                    } catch (GlassFishException e) {
                        e.printStackTrace();
                    }
                }
            }));
            glassfish.start();

            // warデプロイ
            Deployer deployer = glassfish.getDeployer();

            deployer.deploy(new File("./target/jax-rs-test.war"),
                    "--name=jax-rs-test", "--contextroot=/", "--force=true");
        } catch (GlassFishException e) {
            e.printStackTrace();
            /*
             * } catch (URISyntaxException e) { e.printStackTrace();
             */
        }
    }
}