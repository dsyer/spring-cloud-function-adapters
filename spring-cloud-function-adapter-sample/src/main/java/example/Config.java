/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class Config {

	private Properties props;

	@Autowired
	public Config(Properties props) {
		this.props = props;
	}

	@Bean
	public Function<Map<String, Object>, Map<String, Object>> function() {
		return m -> m.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey(),
						e -> e.getValue().toString().toUpperCase()
								+ (props.getFoo() != null ? "-" + props.getFoo() : "")));
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Config.class, args);
	}

}
