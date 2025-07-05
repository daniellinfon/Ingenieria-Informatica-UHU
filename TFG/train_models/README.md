
## Uso

En los cuadernos se indica paso a paso cómo entrenar los modelos para resolver la tarea.

Recuerda que necesitas tener el conjunto de datos en tu Google Drive, por lo que deberás dar acceso al cuaderno de Colab a tu nube.

    from google.colab import drive
    drive.mount('/content/drive')

Los cuadernos asumen que los archivos están guardados en:

    "/content/drive/MyDrive/TFG/data/"

Cada cuaderno entrena y evalúa un modelo diferente (BERT/RoBERTa o T5), realiza ajustes de hiperparámetros con Optuna y registra las curvas de pérdida y matrices de confusión.