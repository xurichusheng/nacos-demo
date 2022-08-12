package com.wjh.conf;

import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * MyBatis Plus分页配置
 * 
 * @author wenjianhai
 * @date 2022/8/10
 * @since JDK 1.8
 */
@Configuration
public class MybatisPlusConfig {
	/**
	 * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor =
	 * false 避免缓存出现问题
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

		PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
		// 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
		pageInterceptor.setOverflow(true);
		// 设置最大单页限制数量，默认 500 条，-1 不受限制
		pageInterceptor.setMaxLimit(500L);

		interceptor.addInnerInterceptor(pageInterceptor);
		return interceptor;
	}

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {

			/**
			 * Customize the given a {@link MybatisConfiguration} object.
			 *
			 * @param configuration the configuration object to customize
			 */
			@Override
			public void customize(MybatisConfiguration configuration) {
				configuration.setCacheEnabled(true);
				configuration.setMapUnderscoreToCamelCase(true);
				configuration.setCallSettersOnNulls(true);
				configuration.setJdbcTypeForNull(JdbcType.NULL);
			}
		};
	}
}
