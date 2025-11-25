# API Testing Framework

Spring Boot 驱动的 API 自动化测试框架，内置 RestAssured、TestNG、环境切换、全局 hooks 以及前端展示页面。

## 快速开始

```bash
# 运行测试（默认 dev 环境，随机端口）
mvn test

# 切换环境，例如 qa
mvn test -Dtest.env=qa

# 启动示例应用和前端页面
mvn spring-boot:run
```

打开浏览器访问 `http://localhost:8080` 可查看环境信息与示例端点说明。

## 目录结构

- `config`：环境配置与属性绑定
- `client`：RestAssured 客户端封装
- `model`：接口数据模型
- `utils`：请求规格、日志、全局 TestNG hooks
- `web`：示例 API 与前端页面
- `test`：基于 TestNG 的测试用例

## 关键特性

- **RestAssured + TestNG**：开箱即用的 HTTP 验证能力与测试生命周期管理
- **环境切换**：通过 `test.env` 系统属性激活 `dev/qa` 配置文件
- **全局 hooks**：`LoggingTestListener` 记录 Suite、Context 与用例的执行日志
- **前端页面**：Thymeleaf 首页展示当前 baseUrl、超时及端点说明

## 示例端点

- `GET /api/health` 健康检查
- `GET /api/users` 示例用户列表
