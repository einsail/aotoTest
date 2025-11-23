# API 测试自动化框架（Java）

该示例项目提供一套基于 Spring Boot 的接口自动化测试框架，支持通过“页面”模型组织操作、用例管理、用例执行，并为后续持续集成扩展预留了空间。

## 主要特性
- **页面操作模型**：`PageActionHandler` 将接口操作抽象成“页面”，内置 `rest` 页面可发起任意 HTTP 请求；注册机制可扩展更多页面类型（如 UI、MQ 等）。
- **用例管理**：`/api/cases` 提供增删改查接口，默认使用内存仓库，后续可替换为数据库存储。
- **用例执行**：`/api/executions/{caseId}` 会按步骤调用对应页面执行动作，汇总执行结果与响应信息。
- **示例数据与回声接口**：启动时自动生成一个访问本地 `/api/echo` 的示例用例，便于快速验证。
- **测试覆盖**：`ExecutionServiceTest` 使用 `MockRestServiceServer` 验证页面执行与结果判定逻辑。

## 快速开始
```bash
mvn clean package
java -jar target/api-test-framework-0.0.1-SNAPSHOT.jar
```
启动后可尝试：
- 查看示例用例列表：`GET http://localhost:8080/api/cases`
- 执行示例用例：`POST http://localhost:8080/api/executions/{caseId}`（将 `{caseId}` 替换为列表中的 ID）
- 回声接口：`GET/POST http://localhost:8080/api/echo`

## 设计说明
- **领域模型**：`TestCase`（用例）、`TestStep`（步骤）、`ExecutionResult`/`StepResult`（执行结果）定义了用例与执行的核心数据结构。
- **页面注册机制**：`PageActionRegistry` 统一注册并按名称分发页面；新增页面时实现 `PageActionHandler` 并在构造函数中注册即可。
- **执行服务**：`ExecutionService` 逐步执行用例并统计整体状态，未注册的页面会返回失败结果提示。
- **可扩展性**：
  - 数据存储可从内存替换为数据库或远程服务。
  - 可在 CI/CD 中通过 `mvn test` 或自定义 Runner 触发执行；可进一步接入调度或消息队列。
  - 可为 `rest` 页面增加断言、鉴权、重试等能力，或新增其他协议页面。

## 运行测试
项目已在 `.mvn/settings.xml` 中预置了阿里云公共仓库镜像，可避免直连 Maven Central 时出现 403 等网络问题。若仍有网络限制，可将镜像地址替换为本地私服。
```bash
mvn test
```

