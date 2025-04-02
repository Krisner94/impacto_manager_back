package application.impacto_manager_back.config.openApi;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    public interface DataDocs {
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "List all", description = "Returns a list of all items")
        @SwaggerDocs.DefaultResponses
        @interface FindAll {}
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "Find by ID", description = "Returns details of an item by ID")
        @SwaggerDocs.DefaultResponses
        @interface FindById {}
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "Find by name", description = "Returns details of an item by name")
        @SwaggerDocs.DefaultResponses
        @interface FindByName {}
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "Find by CPF", description = "Returns details of an item by CPF")
        @SwaggerDocs.DefaultResponses
        @interface FindByCPF {}
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "Create new", description = "Creates a new item")
        @SwaggerDocs.DefaultResponses
        @interface Create {}
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "Update", description = "Updates an existing item")
        @SwaggerDocs.DefaultResponses
        @interface Update {}
    
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @Operation(summary = "Delete", description = "Deletes an item")
        @SwaggerDocs.DefaultResponses
        @interface Delete {}
    }