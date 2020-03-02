/*
 * Copyright 2012, Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.LinkBench;

import java.util.List;
import java.util.Properties;

/**
 * An abstract class for storing both nodes and edges
 * @author tarmstrong
 */
public abstract class GraphStore extends LinkStore implements NodeStore {
  protected boolean check_count = false;
  protected Level debuglevel;
  protected Logger logger;

  public GraphStore() {
    logger = Logger.getLogger();
  }

  public void initialize(Properties props, Phase phase, int threadId) {
    debuglevel = ConfigUtil.getDebugLevel(props);
    if (props.containsKey(Config.CHECK_COUNT))
      check_count = ConfigUtil.getBool(props, Config.CHECK_COUNT);
  }

  /** Provide generic implementation */
  public long[] bulkAddNodes(String dbid, List<Node> nodes) throws Exception {
    long ids[] = new long[nodes.size()];
    int i = 0;
    for (Node node: nodes) {
      long id = addNode(dbid, node);
      ids[i++] = id;
    }
    return ids;
  }
}
