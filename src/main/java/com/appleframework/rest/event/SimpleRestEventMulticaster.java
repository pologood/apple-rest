/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-6-2
 */
package com.appleframework.rest.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class SimpleRestEventMulticaster extends AbstractRestEventMulticaster {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Executor executor;

	@SuppressWarnings("unchecked")
	@Override
    public void multicastEvent(final RestEvent event) {
        try {
            for (final RestEventListener listener : getRestEventListeners(event)) {
                Executor executor = getExecutor();
                if (executor != null) {
                    executor.execute(new Runnable() {
						@Override
                        public void run() {
                            listener.onRestEvent(event);
                        }
                    });
                } else {
                    listener.onRestEvent(event);
                }
            }
        } catch (Exception e) {
            logger.error("处理"+event.getClass().getName()+"事件发生异常",e);
        }
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}

