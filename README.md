# 🧮 Calculator — JavaFX Desktop Calculator
![License](https://img.shields.io/github/license/Muimi272/Calculator)
![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Stars](https://img.shields.io/github/stars/Muimi272/Calculator?style=social)
![Last Commit](https://img.shields.io/github/last-commit/Muimi272/Calculator)

这是一个使用 **JavaFX** 编写的桌面计算器项目，支持基本四则运算、百分比、符号切换、小数输入等功能。项目使用 Maven 管理源码，也可以打包成 Windows 可执行程序分发给普通用户。

This is a **JavaFX desktop calculator application** implementing basic arithmetic, percentage, sign toggle, decimal handling, and intuitive UI. The source code uses Maven and can also be packaged into a distributable Windows executable.

---

## 📦 Repository Structure / 仓库结构

```
Calculator/                # 项目根目录
├─ src/                   # Java 源代码
│   └─ main/java/...      # 你的 JavaFX 应用代码
├─ pom.xml                # Maven 项目配置
└─ README.md              # 项目说明（本文件）
```

当前仓库只包含源码，不包含可执行发布文件（如 `.exe` 与 `app/` 运行时）。([GitHub][1])

---

## 📌 Features / 功能说明

本应用支持以下交互功能：

* 数字输入（`0–9`）
* 小数点 `.`
* 基本四则运算：加 `+`、减 `-`、乘 `×`、除 `÷`
* 百分比 `%`
* 正负号切换 `±`
* 删除键 `⌫`（退格）
* 全部清空 `C`
* 显示当前公式与结果

Features include:

* Number input (`0–9`)
* Decimal (`.`)
* Add (`+`), Subtract (`-`), Multiply (`×`), Divide (`÷`)
* Percentage (`%`)
* Sign toggle (`±`)
* Backspace (`⌫`)
* Clear all (`C`)
* Formula & result display

---

## 🛠 Running from Source / 从源码运行

如果你希望编译或调试源码：

### Requirements / 前提要求

* **Java 17+** 或更高 JDK
* **JavaFX SDK** (本地环境需要添加 JavaFX 依赖)
* Maven

### Build & Run / 编译与运行

1. 克隆仓库：

```bash
git clone https://github.com/Muimi272/Calculator.git
cd Calculator
```

2. 使用 Maven 编译：

```bash
mvn clean compile
```

3. 使用 IDE（如 IntelliJ IDEA / Eclipse）打开项目并运行主类。

---

## 🚀 Packaging to Windows Executable / 打包成 Windows 可执行程序

你可以使用 **jpackage** 打包成可分发的 Windows 程序：

```bash
mvn clean package
jpackage \
  --name Calculator \
  --app-version 1.0 \
  --input target/ \
  --main-jar calculator-1.0.jar \
  --main-class com.muimi.calculator.YourMainClass \
  --type exe \
  --runtime-image path/to/javafx-runtime
```

📌 确保将 `--main-class` 与你的真实主类路径替换成你项目中的入口类。

打包完成后生成的结构类似：

```
Calculator.exe
app/       ← Java 运行时
```

---

## ▶️ Running Packaged App / 运行发布版本

如果你已经有打包好的发布文件（`.exe + app/` 文件夹），请：

1. 解压发布压缩包
2. 确保 `Calculator.exe` 与 `app/` 在同一目录
3. 双击 **Calculator.exe** 启动

无需单独安装 Java；程序自带运行时环境。

---

## 📖 How to Use / 使用说明

### Basic Operations / 基本操作

1. 点击数字按钮输入数字
2. 点击运算符（`+ - × ÷`）
3. 点击第二个数字
4. 点击 `=` 获取结果

### Other Buttons / 其他按钮

| Button | Meaning / 功能        |
| ------ | ------------------- |
| `.`    | Decimal point / 小数点 |
| `%`    | Percentage / 百分比计算  |
| `±`    | Toggle sign / 正负号切换 |
| `⌫`    | Backspace / 删除最后一位  |
| `C`    | Clear all / 清空重置    |

---

## ❗ Error Handling / 错误提示

当发生非法运算（例如除以 0 或格式非法）时，显示屏会显示：

```
ERROR
```

此时请按 `C` 重置。

---

## 💡 Screenshots / 截图（可选）

你可以将程序界面截图放在这个位置 (README 中) 进行展示。

```markdown
![Calculator UI](path/to/screenshot.png)
```

---

## 🧑‍💻 Contributing / 贡献

欢迎提交 issues 或 pull requests 改进项目！

Feel free to open issues or submit pull requests to improve this project!

---

## 📄 License / 协议

本项目基于 MIT License 开源，详情请查看 [LICENSE](LICENSE) 文件。

This project is licensed under the MIT License.


[1]: https://github.com/Muimi272/Calculator "GitHub - Muimi272/Calculator"
