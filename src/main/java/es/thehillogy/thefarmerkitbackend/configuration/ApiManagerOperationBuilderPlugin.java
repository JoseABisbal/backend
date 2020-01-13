package es.thehillogy.thefarmerkitbackend.configuration;

import java.util.Collections;

import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

public class ApiManagerOperationBuilderPlugin implements OperationBuilderPlugin
{
   @Override
   public void apply(OperationContext context)
   {              
      context.operationBuilder().extensions(
            Collections.singletonList(
                  new StringVendorExtension(
                        "x-throttling-tier", "Unlimited")));
   }

   @Override
   public boolean supports(DocumentationType delimiter)
   {
	   return true;
   }
}
