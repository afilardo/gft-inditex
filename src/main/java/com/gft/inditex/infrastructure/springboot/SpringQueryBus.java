package com.gft.inditex.infrastructure.springboot;

import com.gft.inditex.application.querybus.shared.Query;
import com.gft.inditex.application.querybus.shared.QueryBus;
import com.gft.inditex.application.querybus.shared.QueryHandler;
import com.gft.inditex.application.querybus.shared.exceptions.NoQueryHandlerFound;
import com.gft.inditex.domain.common.exceptions.DomainError;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SpringQueryBus implements QueryBus {
  private Map<Class, QueryHandler> handlers;

  public SpringQueryBus(List<QueryHandler> queryHandlerImplementations) {
    this.handlers = new HashMap<>();
    queryHandlerImplementations.forEach(queryHandler -> {
      Class queryClass = getQueryClass(queryHandler);
      handlers.put(queryClass, queryHandler);
    });
  }

  @Override
  public <T> T handle(Query<T> query) throws DomainError {
    if (!handlers.containsKey(query.getClass())) {
      throw new NoQueryHandlerFound(String.format("No handler found for %s", query.getClass().getName()));
    }
    return (T) handlers.get(query.getClass()).handle(query);
  }

  private Class<?> getQueryClass(QueryHandler handler) {
    Type commandInterface = ((ParameterizedType) handler.getClass()
        .getGenericInterfaces()[0]).getActualTypeArguments()[1];
    return getClass(commandInterface.getTypeName());
  }

  private Class<?> getClass(String name) {
    try {
      return Class.forName(name);
    } catch (Exception e) {
      return null;
    }
  }
}
