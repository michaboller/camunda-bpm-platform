package org.camunda.bpm.integrationtest.functional.ejb.remote.bean;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.integrationtest.util.TestConstants;

import javax.inject.Named;
import javax.naming.InitialContext;


/**
 * A CDI bean delegating to the remote business
 * interface of a Singleton Bean from a different application.
 *
 * @author Daniel Meyer
 *
 */
@Named
public class RemoteSingletonBeanClientDelegateBean implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    BusinessInterface businessInterface = (BusinessInterface) new InitialContext().lookup("java:global/" +
        TestConstants.getAppName() +
        "service/" +
        "RemoteSingletonBean!org.camunda.bpm.integrationtest.functional.ejb.remote.bean.BusinessInterface");

    execution.setVariable("result", businessInterface.doBusiness());
  }

}
