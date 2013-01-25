# 安心、簡単！Gradle

## 自己紹介
悪くないモンスターの太一です。  
最近はjsばっかり書いてますけども一番得意な言語はJavaです。  

- プログラマ
    - [ryushi](https://twitter.com/ryushi)@twitter
    - [taichi](https://github.com/taichi)@GitHub
- ゲーマー
    - taichi#1881 @ Diablo3
    - [DQ7](http://www.dragonquest.jp/dq7/) 待ち

### 最近の成果

#### [Rappa](https://github.com/taichi/rappa)
GitHubでコードリーディングする為のChrome拡張

#### [Yuzen](https://github.com/taichi/yuzen)
markdownからhtmlを生成するGradleプラグイン


## アジェンダ
- Javaにおけるビルドツールの歴史
- [Groovy]とは何か
- [Gradle]とは何か
- [Graldeを使ったビルドスクリプト](https://github.com/taichi/toyama_201301/blob/master/build/simple/build.gradle)
- [Gradle]への移行
- Gradle Wrapper
- [本格的なビルドスクリプト](https://github.com/taichi/toyama_201301/blob/master/build/complex/build.gradle)
- まとめ


## Javaにおけるビルドツールの歴史

### [Make](http://www.gnu.org/software/make/)
- タスクベースの自動化
- タスク間の依存関係定義

恐らく最初はみんなMakeかシェルスクリプトだった筈。 と言うか僕がそうだった。  
しかし、OSを越えた相互運用性が低い為使い辛かった。  
要はwindowsでconfigureシェルを簡単には動かせない。


### [Ant](http://ant.apache.org)
- ビルド込みで __Write once, run anywhere__ が現実的になった。
- XMLによる定型作業の定義
- シンプルなプラグインシステム
- eclipse に標準でランチャが組込まれている。


### ビルドという仕事をXMLで表現可能であるという過ち
十分に分析され抽象化された定型作業だけを自動化するのであればXMLでよい。  
ビルドの役割をその様に小さくしてしまうとソフトウェア開発における自由度が大きく制限される事になる。  
例えば、ソースコードの自動生成に関連する技術を利用するのが難しくなってしまう。  
つまり、ビルドは非定型作業をアドホックに自動化するものと考える方が望ましい。  
しかし、プロジェクトの進歩に従ってビルドは仕事が複雑化し、そこに矛盾が集約されていく事になる。  
よってXMLの様に冗長な構文でビルドを記述するのは、凡そ妥当でない。  


### [Maven](http://maven.apache.org/)
- ビルドを中心にソフトウェアプロジェクトを正面からオブジェクト指向分析した。
    - ビルドスクリプトにおける[ライフサイクルモデルの定義](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
    - 依存ライブラリを、その推移的依存性も含めて一度にダウンロード出来る様にした。
    - [Mojo](http://maven.apache.org/developers/mojo-api-specification.html)を中心とした複雑なプラグインシステム
- Maven Central Repository という巨大なライブラリ置き場を定義し運用した。
    - ASFのサーバであったが、その恐るべき利便性によって広く使われる事になった。
- 全てが冗長で巨大であるが標準ルールに従うとpom.xmlだけは小さくなる。
    - ディレクトリ構成やモジュール配置に関して、標準的な構成を示した。
    - Mavenによって定義された標準構成は極めて冗長であった。


### [Gradle]
Mavenが持つほぼ唯一にして最大最悪の欠点はXMLベースの技術である事。  
これを動的言語である[Groovy]によって置き換えたのが[Gradle]。


### 隣の世界
- [sbt](https://github.com/sbt/sbt/)
    - Simple Build Tool という Scala用のビルドツール。
    - Simpleとは何なのか考えるきっかけになるのでオススメ。
- [Grunt.js](http://gruntjs.com/)
    - Node.js で動作するタスクベースのビルドツール。
    - Ant相当の機能を持つ。
    - Nodeにはnpmというモジュールの依存性解決ツールがある。
- [Rake](http://rake.rubyforge.org/)
    - Rubyのビルドツール
    - モジュールの依存性解決やバージョンアップには [RubyGems](http://rubygems.org/)を使う

## [Groovy]とは何か

Javaに最も近いJVM言語である。  
次ページからJavaのコードをGroovyのコードに簡単な手続きで変更していく。

### HelloWorld.java を Groovyらしくする。

- [ファイルの拡張子を.groovyに変更する。](https://github.com/taichi/toyama_201301/commit/1512c6a8307cbfd09a75b21408a666c5ab1c0a5d)
    - 例外はあるがJavaのコードはGroovyのコードであると考えて良い
- [セミコロンを省略する事が出来る。](https://github.com/taichi/toyama_201301/commit/c54a6d160dc91b4a1aeb30118446dca4d661c1bb)
    - 無関係な参考資料.1 [Javaでセミコロンなしでプログラムを書く](http://d.hatena.ne.jp/Nagise/20100321/1269182606)
    - 無関係な参考資料.2 [セミコロンレスJavaの構造化＆オブジェクト指向](http://d.hatena.ne.jp/Nagise/20110821/1313914797)
- [デフォルトのスコープはpublicである。](https://github.com/taichi/toyama_201301/commit/a10e3d45253a8adab4a0c959449f907bf873d969)
- [最外周の丸かっこは省略する事が出来る。](https://github.com/taichi/toyama_201301/commit/fd0ec96d9412109bfa8aed4e26c30383a89a1fb9)
- [便利なショートカットメソッドがグローバル空間(GroovyDefaultMethods)に定義されている。](https://github.com/taichi/toyama_201301/commit/c9c44efbb5f1b340bbf8c4f9a64ce072f7376b84)
- [スクリプトファイルとして使用出来る。](https://github.com/taichi/toyama_201301/commit/fb6bde5833d95f21c5aadb072f6853cc4cef6dd6)

### プロパティ構文と文字列処理
ここからはGroovyのより便利な機能を紹介する。  
全ての機能を紹介する訳では無く太一が良く使っている利便性の高いものに絞って説明する。

- [プロパティを宣言する。](https://github.com/taichi/toyama_201301/commit/d90470d8ec488d7d7c57b88b299502041fb1b69a)
- [Power Assertとプロパティを使用する。](https://github.com/taichi/toyama_201301/commit/d2c109aa8c4de15152c5b868cbc68090ccc26555)
- [オブジェクトの生成時にプロパティを設定する。](https://github.com/taichi/toyama_201301/commit/ef993badd83d39c8c96b678f2f8c9c91e9b73556)
- [toJsonメソッドの追加。](https://github.com/taichi/toyama_201301/commit/71c29ba8eb51f7d69f85e55f62be0972d656aba2)
- [returnを省略する事が出来る。](https://github.com/taichi/toyama_201301/commit/108262f0d3ae3d147cad077166bc7b9d8b436ddf)
- [文字列をGStringによって表現する。](https://github.com/taichi/toyama_201301/commit/d1351301edd829e935a794bd51f8fa388310ea3a)
    - ダブルクォートはGStringというテンプレート機能を持った文字列である。
    - シングルクォートによってStringを表現する。
    - Groovyにはキャラクタリテラルは存在しない。

#### クロージャ
Java8 でついに[ラムダ](http://openjdk.java.net/projects/lambda/)が導入されるがGroovyのクロージャとは異なる。  
しかしながら、多くの単純なケースでは良く似たコードで、良く似た振る舞いになる。  

- [Javaによるリストの中身を大文字化するループ処理]()
- [配列の初期化で生成されるのはList。]()
- [ループ処理をクロージャを使ったものに置き換える。]()

### [Groovy] 参考資料
- [Easy Going Groovy 2nd season on DevLOVE](http://www.slideshare.net/uehaj/dev-love-groovy2011)
- [From Java to Groovy in a few easy steps](http://groovy.dzone.com/news/java-groovy-few-easy-steps)
- [Groovy JDK](http://groovy.codehaus.org/groovy-jdk/)


### 他のJVM言語
- [JRuby](http://jruby.org/)
- [Jython](http://www.jython.org/)
- [Nashorn](http://openjdk.java.net/projects/nashorn/)
- [Clojure](http://clojure.org/)
- [Scala](http://www.scala-lang.org/)


## [Gradle]とは何か

### 事例紹介
- LinkedIn
- Netflix
- Spring
- Hibernate

## Gradleの起動オプション
以下の環境変数を設定する。  

- メモリサイズとエンコーディングの設定  
```
    set JAVA_OPTS=-Xms128m -Xmx4096m -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 
```
- プロキシの設定  
```
    set GRADLE_OPTS=-Dhttp.proxyHost=example.jp -Dhttp.proxyPort=8080 -Dhttps.proxyHost=example.jp -Dhttps.proxyPort=8080
```

## [Graldeを使ったビルドスクリプト](https://github.com/taichi/toyama_201301/blob/master/build/simple/build.gradle)

### Javaプロジェクト
```groovy
apply plugin: 'java'
group = 'jp.example'
version = '0.1.0'
repositories { mavenCentral() }
dependencies {  
  compile 'com.google.guava:guava:13.+'
  testCompile 'junit:junit:4.+'
}
targetCompatibility = sourceCompatibility = 1.7
tasks.withType(AbstractCompile) each { it.options.encoding = 'UTF-8' }
```

### eclipse プロジェクトの自動生成
```groovy
apply plugin: 'eclipse'
eclipse {
  classpath {
    containers = [
      'org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.7'
    ]
  }
}
```

### テストコードの実行
```
gradlew test
```

### リリース用アーカイブの作成
```
gradlew jar
```

## [Gradle]への移行

### [Antタスクの呼び出し](http://gradle.org/docs/current/userguide/ant.html)

- build.gradle

```groovy
ant.importBuild 'build.xml'
```

- build.xml

```xml
<project>
  <target name="hello"><echo>Hello, from Ant</echo></target>
</project>
```

```
gradlew hello
```

## Gradle Wrapper

### インストールに関する問題

新しいビルドツールをプロジェクトメンバにインストールして貰うのは非常に大きなコストがかかる。   
それぞれの人間がインストール作業する場合、ビルドツールのバージョンを適切に固定するのが難しい。  
これはビルドツールが互換性のない大きな変更を、それなりの頻度で行う場合に問題になる。  
gradleはまだ若いプロダクトである為、頻繁に大きな機能追加を行なっている。  


### 内容物
- 50KB未満の小さなjarファイル
- 設定が格納されたpropertiesファイル
- UNIX系OS向けbashスクリプト
- Windows系OS向けのNTスクリプト

これらをソースコード管理しているリポジトリにコミットし、そこからGradleを使って貰う。

### Gradle Wrapperの動作
__gradlew__コマンドを実行した際にローカルディスクにGradleがインストールされていない場合、  
gradle.orgから実行バイナリをダウンロードしてくる。  
設定されたバージョン番号とインストール済のバージョン番号がズレていてもダウンロードしてくる。  
尚、ダウンロードしてくるURLを任意の値にカスタマイズする事が出来る。  

## [本格的なビルドスクリプト](https://github.com/taichi/toyama_201301/blob/master/build/complex/build.gradle)

### ディレクトリ構造のカスタマイズ

```groovy
sourceSets {
  main {
    java.srcDir 'src'
    resources.srcDirs 'src'
  }
  test {
    java.srcDir 'test'
    resources.srcDir 'test'
  }
}
```

### ビルドスクリプトの分割

```groovy
group = 'jp.example'
version = '0.1.0'
apply from: file('gradle/convention.gradle')
apply from: file('gradle/webapp.gradle')
repositories { mavenCentral() }
dependencies {
  compile 'com.google.guava:guava:13.+'
  providedCompile "javax.servlet:javax.servlet-api:3.0.1"
  testCompile 'junit:junit:4.+'
}
apply from: file('gradle/ide.gradle')
```

### デプロイ

#### [Warファイルの構築](http://gradle.org/docs/current/userguide/war_plugin.html)
```groovy
apply plugin: 'war'
webAppDirName = 'webapp'
```

#### Tomcatの起動/停止
```groovy
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath 'org.gradle.api.plugins:gradle-tomcat-plugin:0.9.6'
  }
}
apply plugin: org.gradle.api.plugins.tomcat.TomcatPlugin
dependencies {
  def tomcatVersion = '7.0.11'
  def gid = 'org.apache.tomcat.embed'
  tomcat "$gid:tomcat-embed-core:${tomcatVersion}",
       "$gid:tomcat-embed-logging-juli:${tomcatVersion}"
  tomcat("$gid:tomcat-embed-jasper:${tomcatVersion}") {
    exclude group: 'org.eclipse.jdt.core.compiler', module: 'ecj'
  }
}
```

### アドホックな処理の追加
```groovy
war {
  doFirst {
    def f = file('gradle/number')
    def no = 0;
    if(f.exists()) {
      no = f.text
    } else {
      f.text = no
    }
    war.classifier = f.text = ++no
  }
}
```

## まとめ
- テストコードだけでも[Groovy]で書くと生産性が大きく向上するのでオススメ
- [Gradle]に移行して高度なビルドスクリプトをバリバリ書こう！


[Gradle]: http://www.gradle.org/
[Groovy]: http://groovy.codehaus.org/
