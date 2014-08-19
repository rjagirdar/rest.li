/*
   Copyright (c) 2014 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.linkedin.restli.common.testutils;


import com.linkedin.data.schema.RecordDataSchema;
import com.linkedin.data.template.DataTemplate;
import com.linkedin.data.template.DataTemplateUtil;
import com.linkedin.data.template.DynamicRecordMetadata;
import com.linkedin.data.template.FieldDef;
import com.linkedin.restli.common.ActionResponse;

import java.util.Collections;


/**
 * Factory to mock {@link ActionResponse}.
 *
 * @author Keren Jin
 */
public class MockActionResponseFactory
{
  private MockActionResponseFactory() { }

  /**
   * Create an {@link ActionResponse} with specified value class and data.
   *
   * @param clazz value class of the response
   * @param value value data of the response
   * @param <T> type of the value class
   * @return mocked {@link ActionResponse}
   */
  public static <T extends DataTemplate<?>> ActionResponse<T> create(Class<T> clazz, T value)
  {
    final FieldDef<T> fieldDef = new FieldDef<T>(ActionResponse.VALUE_NAME, clazz, DataTemplateUtil.getSchema(clazz));
    final RecordDataSchema entitySchema = DynamicRecordMetadata.buildSchema(ActionResponse.class.getName(), Collections.<FieldDef<?>>singletonList(fieldDef));
    if (value == null)
    {
      return new ActionResponse<T>(fieldDef, entitySchema);
    }
    else
    {
      return new ActionResponse<T>(value, fieldDef, entitySchema);
    }
  }
}