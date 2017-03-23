package com.lu;

import com.lu.operation.impl.BaseOperation;
import com.lu.operation.impl.ExtensionOperations;

public class OperationExecutor {
    public static void main(String[] args) {
        for (BaseOperation baseOperation : BaseOperation.values()) {
            baseOperation.apply(1,2);
        }
        for (ExtensionOperations extensionOperations : ExtensionOperations.values()) {
            extensionOperations.apply(5,4);
        }
    }


}
