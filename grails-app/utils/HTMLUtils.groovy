class HTMLUtils {
  static String replaceNewlines(String value) {
    return value?.replaceAll(/\n|\r\n/, '<br />')
  }
}