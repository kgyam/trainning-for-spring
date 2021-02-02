import org.springframework.beans.factory.annotation.Value;
/**
 * 依赖来源的训练包
 * <p>
 * 依赖主要来自四个地方：
 * 1. 通过BeanDefinition构建的Bean
 * 2. Singleton
 * 3. Resolve Dependency
 * 4. 外部化配置【外部化配置可以通过{@link org.springframework.beans.factory.annotation.Value}注入,处理逻辑在AutowiredAnnotationBeanPostProcessor中】
 *
 * @author kg yam
 * @date 2021-01-20 14:29
 * @since
 */