JavaLaunchScriptAppについて
=============
---------------
概要
--------
Mac OS X 10.6 - 10.8を対象に、Java6/Java7環境にて、Javaアプリケーションを起動するためのBashによるスクリプトを、アプリケーションバンドルとして実行できるようにするサンプル(スケルトン)です。

Javaアフリケーションは以下のようにJarファイルとしています。

 - JavaLaunchExample.app//Contents/Resources/Java/SimpleJavaApp.jar

javaプロセスをキックするためのシェルスクリプトによる以下のスタブから起動されます。

- JavaLaunchExample.app//Contents/MacOS/JavaLaunchExample.sh

---------------
ビルド方法
---------------

### Javaコード ###

ターミナル上からJavacにパスが通っていれば、
    ./build.sh
でビルド、Jar化を行い、アプリケーションバンドル内に配置します。

このとき、シェルの実行ビット、アプリケーションバンドルとしてのB属性も設定します。

githubよりcloneしたばかりの場合は、これらの属性が設定されていないため、最初にbuildして属性を設定する必要があります。


### スタブ ###

スタブとなるシェルスクリプトは、単なるシェルスクリプトであるため、普通に編集できます。

build.sh実行時に実行ビットが設定されます。


---------------
ユーザーデフォルトによるヒープサイズの指定
---------------
スタブのシェルスクリプトには、ユーザデフォルトを読み取り、Javaの起動オプションとしてヒーフサイズを設定するようになっています。

ヒープサイズはターミナルより、以下のように、それぞれ最大・最小ヒープサイズを指定できます。
指定ない場合は、そのオプションは起動オプションとして設定されません。

    defaults write jp.seraphyware.javalaunchexample.SimpleJavaApp maxHeap 512m
    defaults write jp.seraphyware.javalaunchexample.SimpleJavaApp minHeap 128m
