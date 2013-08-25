#! /bin/bash
# スクリプトのディレクトリを求める
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# アプリケーション名等の定義
JARNAME="SimpleJavaApp.jar"
APPNAME="jp.seraphyware.javalaunchexample.SimpleJavaApp"

# Java7用のファイルエンコーディングの指定
if [ -z "$LANG" -a -z "$LC_CTYPE" ]; then
	export LC_CTYPE="UTF-8"
fi

# ユーザデフォルトからヒープサイズの指定を読み取る
# 指定ない場合は64mとする.
MAX_HEAP=$(defaults read "$APPNAME" maxHeap)
MIN_HEAP=$(defaults read "$APPNAME" minHeap)

# Java起動オプションの構築
JAVA_OPT=""
if [ ! -z "$MAX_HEAP" ]; then
	JAVA_OPT="$JAVA_OPT -Xmx${MAX_HEAP}"
fi
if [ ! -z "$MIN_HEAP" ]; then
	JAVA_OPT="$JAVA_OPT -Xms${MIN_HEAP}"
fi
echo "$JAVA_OPT"

# Javaプロセスの起動
java  $JAVA_OPT -cp "$DIR/../Resources/Java/$JARNAME" $APPNAME
