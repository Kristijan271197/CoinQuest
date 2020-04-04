//package com.kristijanstojanoski.game;
//
//import States.MusicSounds;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Preferences;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.math.Rectangle;
//
//class Player {
//  public static final String PLAYER_FALL = "playerFall";
//
//  public static final String PLAYER_GROUND = "playerGround";
//
//  private MusicSounds musicSoundsObject;
//
//  private int pause = 0;
//
//  private TextureAtlas.AtlasRegion[] playerFaint;
//
//  private int playerFaintState = 0;
//
//  private float playerHeight = 0.0F;
//
//  private float playerHeightRect = 0.0F;
//
//  private TextureAtlas.AtlasRegion[] playerHurt;
//
//  private Rectangle playerRectangle;
//
//  private Rectangle playerRectangle2;
//
//  private TextureAtlas.AtlasRegion[] playerRun;
//
//  private int playerState = 0;
//
//  private float playerWidth = 0.0F;
//
//  private float playerWidthRect = 0.0F;
//
//  private float playerX = 0.0F;
//
//  private float playerXRect = 0.0F;
//
//  private float playerY = 0.0F;
//
//  private TextureAtlas.AtlasRegion tapToStart;
//
//  private float velocity = 0.0F;
//
//  private void playerFaintState(int paramInt1, int paramInt2) {
//    int i = this.pause;
//    if (i < paramInt1) {
//      this.pause = i + 1;
//    } else {
//      this.pause = 0;
//      paramInt1 = this.playerFaintState;
//      if (paramInt1 < paramInt2)
//        this.playerFaintState = paramInt1 + 1;
//    }
//  }
//
//  private void playerRunState(int paramInt1, int paramInt2, int paramInt3) {
//    int i = this.pause;
//    if (i < paramInt1) {
//      this.pause = i + 1;
//    } else {
//      this.pause = 0;
//      paramInt1 = this.playerState;
//      if (paramInt1 < paramInt2) {
//        this.playerState = paramInt1 + 1;
//      } else {
//        this.playerState = 0;
//      }
//    }
//    if (this.playerY + this.playerHeight - worldYToScreenY(paramInt3) >= Gdx.graphics.getHeight())
//      this.velocity = Gdx.graphics.getDeltaTime() * 1.0F * 60.0F;
//  }
//
//  private void setParameters(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
//    this.playerX = worldXToScreenX(paramInt1);
//    this.playerY = worldYToScreenY(paramInt2);
//    this.playerWidth = worldXToScreenX(paramInt3);
//    this.playerHeight = worldYToScreenY(paramInt4);
//    this.playerXRect = this.playerX + worldXToScreenX(paramInt5);
//    this.playerWidthRect = worldXToScreenX(paramInt6);
//    this.playerHeightRect = this.playerHeight - worldYToScreenY(paramInt7);
//  }
//
//  private float worldXToScreenX(float paramFloat) {
//    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
//  }
//
//  private float worldYToScreenY(float paramFloat) {
//    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
//  }
//
//  public void dispose() {}
//
//  void drawPlayerFaint(SpriteBatch paramSpriteBatch, boolean paramBoolean, Preferences paramPreferences) {
//    if (!paramBoolean) {
//      float f1 = worldYToScreenY(0.5F);
//      float f2 = Gdx.graphics.getDeltaTime();
//      f2 = this.velocity + f1 * f2 * 60.0F;
//      this.velocity = f2;
//      f2 = this.playerY - f2;
//      this.playerY = f2;
//      if (f2 <= worldYToScreenY(75.0F)) {
//        if (paramPreferences.getBoolean("playerGround", false)) {
//          this.musicSoundsObject.playPlayerGround();
//          paramPreferences.putBoolean("playerGround", false);
//          paramPreferences.flush();
//        }
//        this.playerY = worldYToScreenY(75.0F);
//        if (paramPreferences.getInteger("costumeSelectedGame") == 0) {
//          int i = this.playerFaintState;
//          if (i < 42)
//            this.playerFaintState = i + 1;
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
//          playerFaintState(2, 9);
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
//          playerFaintState(2, 9);
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
//          playerFaintState(2, 9);
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
//          playerFaintState(2, 9);
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
//          playerFaintState(2, 9);
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
//          playerFaintState(2, 9);
//        } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
//          playerFaintState(3, 7);
//        }
//      }
//      if (this.playerY + this.playerHeight >= Gdx.graphics.getHeight())
//        this.playerY = Gdx.graphics.getHeight() - this.playerHeight;
//    }
//    this.playerX = worldXToScreenX(0.0F);
//    if (paramPreferences.getInteger("costumeSelectedGame") == 0) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX, this.playerY, worldXToScreenX(200.0F), this.playerHeight);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX + worldXToScreenX(85.0F), this.playerY, this.playerWidth, this.playerHeight);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX - worldXToScreenX(5.0F), this.playerY, this.playerWidth + worldXToScreenX(90.0F), this.playerHeight);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX + worldXToScreenX(85.0F), this.playerY, this.playerWidth + worldXToScreenX(90.0F), this.playerHeight);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX + worldXToScreenX(85.0F), this.playerY, this.playerWidth - worldXToScreenX(10.0F), this.playerHeight + worldXToScreenX(15.0F));
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX + worldXToScreenX(90.0F), this.playerY, this.playerWidth + worldXToScreenX(35.0F), this.playerHeight + worldXToScreenX(10.0F));
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX + worldXToScreenX(90.0F), this.playerY, this.playerWidth + worldXToScreenX(60.0F), this.playerHeight + worldXToScreenX(20.0F));
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
//      paramSpriteBatch.draw((TextureRegion)this.playerFaint[this.playerFaintState], this.playerX + worldXToScreenX(80.0F), this.playerY, this.playerWidth, this.playerHeight);
//    }
//  }
//
//  void drawPlayerRun(SpriteBatch paramSpriteBatch, Shield paramShield, Rock paramRock, boolean paramBoolean, ShapeRenderer paramShapeRenderer, Preferences paramPreferences) {
//    // Byte code:
//    //   0: aload_2
//    //   1: invokevirtual isHasShield : ()Z
//    //   4: ifeq -> 24
//    //   7: aload_2
//    //   8: aload_1
//    //   9: aload_0
//    //   10: getfield playerX : F
//    //   13: aload_0
//    //   14: getfield playerY : F
//    //   17: iload #4
//    //   19: aload #6
//    //   21: invokevirtual drawShield : (Lcom/badlogic/gdx/graphics/g2d/SpriteBatch;FFZLcom/badlogic/gdx/Preferences;)V
//    //   24: aload_3
//    //   25: invokevirtual isFirstRockHit : ()Z
//    //   28: ifeq -> 63
//    //   31: aload_1
//    //   32: aload_0
//    //   33: getfield playerHurt : [Lcom/badlogic/gdx/graphics/g2d/TextureAtlas$AtlasRegion;
//    //   36: aload_0
//    //   37: getfield playerState : I
//    //   40: aaload
//    //   41: aload_0
//    //   42: getfield playerX : F
//    //   45: aload_0
//    //   46: getfield playerY : F
//    //   49: aload_0
//    //   50: getfield playerWidth : F
//    //   53: aload_0
//    //   54: getfield playerHeight : F
//    //   57: invokevirtual draw : (Lcom/badlogic/gdx/graphics/g2d/TextureRegion;FFFF)V
//    //   60: goto -> 92
//    //   63: aload_1
//    //   64: aload_0
//    //   65: getfield playerRun : [Lcom/badlogic/gdx/graphics/g2d/TextureAtlas$AtlasRegion;
//    //   68: aload_0
//    //   69: getfield playerState : I
//    //   72: aaload
//    //   73: aload_0
//    //   74: getfield playerX : F
//    //   77: aload_0
//    //   78: getfield playerY : F
//    //   81: aload_0
//    //   82: getfield playerWidth : F
//    //   85: aload_0
//    //   86: getfield playerHeight : F
//    //   89: invokevirtual draw : (Lcom/badlogic/gdx/graphics/g2d/TextureRegion;FFFF)V
//    //   92: aload #6
//    //   94: ldc 'costumeSelectedGame'
//    //   96: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   101: ifne -> 143
//    //   104: aload_0
//    //   105: new com/badlogic/gdx/math/Rectangle
//    //   108: dup
//    //   109: aload_0
//    //   110: getfield playerX : F
//    //   113: aload_0
//    //   114: ldc 25.0
//    //   116: invokespecial worldXToScreenX : (F)F
//    //   119: fadd
//    //   120: aload_0
//    //   121: getfield playerY : F
//    //   124: aload_0
//    //   125: ldc 60.0
//    //   127: invokespecial worldXToScreenX : (F)F
//    //   130: aload_0
//    //   131: getfield playerHeight : F
//    //   134: invokespecial <init> : (FFFF)V
//    //   137: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   140: goto -> 540
//    //   143: aload #6
//    //   145: ldc 'costumeSelectedGame'
//    //   147: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   152: iconst_1
//    //   153: if_icmpne -> 202
//    //   156: aload_0
//    //   157: new com/badlogic/gdx/math/Rectangle
//    //   160: dup
//    //   161: aload_0
//    //   162: getfield playerX : F
//    //   165: aload_0
//    //   166: ldc 50.0
//    //   168: invokespecial worldXToScreenX : (F)F
//    //   171: fadd
//    //   172: aload_0
//    //   173: getfield playerY : F
//    //   176: aload_0
//    //   177: ldc 60.0
//    //   179: invokespecial worldXToScreenX : (F)F
//    //   182: aload_0
//    //   183: getfield playerHeight : F
//    //   186: aload_0
//    //   187: ldc 15.0
//    //   189: invokespecial worldYToScreenY : (F)F
//    //   192: fsub
//    //   193: invokespecial <init> : (FFFF)V
//    //   196: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   199: goto -> 540
//    //   202: aload #6
//    //   204: ldc 'costumeSelectedGame'
//    //   206: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   211: iconst_2
//    //   212: if_icmpne -> 261
//    //   215: aload_0
//    //   216: new com/badlogic/gdx/math/Rectangle
//    //   219: dup
//    //   220: aload_0
//    //   221: getfield playerX : F
//    //   224: aload_0
//    //   225: ldc 35.0
//    //   227: invokespecial worldXToScreenX : (F)F
//    //   230: fadd
//    //   231: aload_0
//    //   232: getfield playerY : F
//    //   235: aload_0
//    //   236: ldc 60.0
//    //   238: invokespecial worldXToScreenX : (F)F
//    //   241: aload_0
//    //   242: getfield playerHeight : F
//    //   245: aload_0
//    //   246: ldc 30.0
//    //   248: invokespecial worldYToScreenY : (F)F
//    //   251: fsub
//    //   252: invokespecial <init> : (FFFF)V
//    //   255: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   258: goto -> 540
//    //   261: aload #6
//    //   263: ldc 'costumeSelectedGame'
//    //   265: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   270: iconst_3
//    //   271: if_icmpne -> 313
//    //   274: aload_0
//    //   275: new com/badlogic/gdx/math/Rectangle
//    //   278: dup
//    //   279: aload_0
//    //   280: getfield playerX : F
//    //   283: aload_0
//    //   284: ldc 35.0
//    //   286: invokespecial worldXToScreenX : (F)F
//    //   289: fadd
//    //   290: aload_0
//    //   291: getfield playerY : F
//    //   294: aload_0
//    //   295: ldc 60.0
//    //   297: invokespecial worldXToScreenX : (F)F
//    //   300: aload_0
//    //   301: getfield playerHeight : F
//    //   304: invokespecial <init> : (FFFF)V
//    //   307: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   310: goto -> 540
//    //   313: aload #6
//    //   315: ldc 'costumeSelectedGame'
//    //   317: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   322: iconst_4
//    //   323: if_icmpne -> 372
//    //   326: aload_0
//    //   327: new com/badlogic/gdx/math/Rectangle
//    //   330: dup
//    //   331: aload_0
//    //   332: getfield playerX : F
//    //   335: aload_0
//    //   336: ldc 60.0
//    //   338: invokespecial worldXToScreenX : (F)F
//    //   341: fadd
//    //   342: aload_0
//    //   343: getfield playerY : F
//    //   346: aload_0
//    //   347: ldc 60.0
//    //   349: invokespecial worldXToScreenX : (F)F
//    //   352: aload_0
//    //   353: getfield playerHeight : F
//    //   356: aload_0
//    //   357: ldc 10.0
//    //   359: invokespecial worldYToScreenY : (F)F
//    //   362: fsub
//    //   363: invokespecial <init> : (FFFF)V
//    //   366: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   369: goto -> 540
//    //   372: aload #6
//    //   374: ldc 'costumeSelectedGame'
//    //   376: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   381: iconst_5
//    //   382: if_icmpne -> 430
//    //   385: aload_0
//    //   386: new com/badlogic/gdx/math/Rectangle
//    //   389: dup
//    //   390: aload_0
//    //   391: getfield playerX : F
//    //   394: aload_0
//    //   395: ldc 30.0
//    //   397: invokespecial worldXToScreenX : (F)F
//    //   400: fadd
//    //   401: aload_0
//    //   402: getfield playerY : F
//    //   405: aload_0
//    //   406: ldc 60.0
//    //   408: invokespecial worldXToScreenX : (F)F
//    //   411: aload_0
//    //   412: getfield playerHeight : F
//    //   415: aload_0
//    //   416: fconst_2
//    //   417: invokespecial worldYToScreenY : (F)F
//    //   420: fsub
//    //   421: invokespecial <init> : (FFFF)V
//    //   424: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   427: goto -> 540
//    //   430: aload #6
//    //   432: ldc 'costumeSelectedGame'
//    //   434: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   439: bipush #6
//    //   441: if_icmpne -> 490
//    //   444: aload_0
//    //   445: new com/badlogic/gdx/math/Rectangle
//    //   448: dup
//    //   449: aload_0
//    //   450: getfield playerX : F
//    //   453: aload_0
//    //   454: ldc 30.0
//    //   456: invokespecial worldXToScreenX : (F)F
//    //   459: fadd
//    //   460: aload_0
//    //   461: getfield playerY : F
//    //   464: aload_0
//    //   465: ldc 60.0
//    //   467: invokespecial worldXToScreenX : (F)F
//    //   470: aload_0
//    //   471: getfield playerHeight : F
//    //   474: aload_0
//    //   475: ldc 5.0
//    //   477: invokespecial worldYToScreenY : (F)F
//    //   480: fsub
//    //   481: invokespecial <init> : (FFFF)V
//    //   484: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   487: goto -> 540
//    //   490: aload #6
//    //   492: ldc 'costumeSelectedGame'
//    //   494: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   499: bipush #7
//    //   501: if_icmpne -> 540
//    //   504: aload_0
//    //   505: new com/badlogic/gdx/math/Rectangle
//    //   508: dup
//    //   509: aload_0
//    //   510: getfield playerX : F
//    //   513: aload_0
//    //   514: ldc 35.0
//    //   516: invokespecial worldXToScreenX : (F)F
//    //   519: fadd
//    //   520: aload_0
//    //   521: getfield playerY : F
//    //   524: aload_0
//    //   525: ldc 70.0
//    //   527: invokespecial worldXToScreenX : (F)F
//    //   530: aload_0
//    //   531: getfield playerHeight : F
//    //   534: invokespecial <init> : (FFFF)V
//    //   537: putfield playerRectangle : Lcom/badlogic/gdx/math/Rectangle;
//    //   540: aload_0
//    //   541: new com/badlogic/gdx/math/Rectangle
//    //   544: dup
//    //   545: aload_0
//    //   546: getfield playerX : F
//    //   549: aload_0
//    //   550: getfield playerY : F
//    //   553: aload_0
//    //   554: getfield playerWidth : F
//    //   557: aload_0
//    //   558: getfield playerHeight : F
//    //   561: invokespecial <init> : (FFFF)V
//    //   564: putfield playerRectangle2 : Lcom/badlogic/gdx/math/Rectangle;
//    //   567: iload #4
//    //   569: ifne -> 1042
//    //   572: getstatic com/badlogic/gdx/Gdx.input : Lcom/badlogic/gdx/Input;
//    //   575: invokeinterface getX : ()I
//    //   580: istore #7
//    //   582: getstatic com/badlogic/gdx/Gdx.input : Lcom/badlogic/gdx/Input;
//    //   585: invokeinterface getY : ()I
//    //   590: istore #8
//    //   592: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
//    //   595: invokeinterface getWidth : ()I
//    //   600: i2f
//    //   601: fstore #9
//    //   603: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
//    //   606: invokeinterface getHeight : ()I
//    //   611: i2f
//    //   612: fstore #10
//    //   614: getstatic com/badlogic/gdx/Gdx.input : Lcom/badlogic/gdx/Input;
//    //   617: invokeinterface justTouched : ()Z
//    //   622: ifeq -> 713
//    //   625: iload #7
//    //   627: iflt -> 659
//    //   630: iload #7
//    //   632: i2f
//    //   633: fload #9
//    //   635: fcmpg
//    //   636: ifgt -> 659
//    //   639: iload #8
//    //   641: iflt -> 659
//    //   644: iload #8
//    //   646: i2f
//    //   647: fload #10
//    //   649: fload #10
//    //   651: ldc 15.0
//    //   653: fdiv
//    //   654: fsub
//    //   655: fcmpg
//    //   656: iflt -> 683
//    //   659: iload #7
//    //   661: i2f
//    //   662: fstore #10
//    //   664: fload #10
//    //   666: fload #9
//    //   668: ldc 7.5
//    //   670: fdiv
//    //   671: fcmpl
//    //   672: ifle -> 713
//    //   675: fload #10
//    //   677: fload #9
//    //   679: fcmpg
//    //   680: ifge -> 713
//    //   683: aload_0
//    //   684: aload_0
//    //   685: ldc 12.5
//    //   687: invokespecial worldYToScreenY : (F)F
//    //   690: fneg
//    //   691: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
//    //   694: invokeinterface getDeltaTime : ()F
//    //   699: fmul
//    //   700: ldc 60.0
//    //   702: fmul
//    //   703: putfield velocity : F
//    //   706: aload_0
//    //   707: getfield musicSoundsObject : LStates/MusicSounds;
//    //   710: invokevirtual playJump : ()V
//    //   713: aload #6
//    //   715: ldc 'costumeSelectedGame'
//    //   717: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   722: ifne -> 796
//    //   725: aload_0
//    //   726: getfield playerState : I
//    //   729: istore #7
//    //   731: iload #7
//    //   733: bipush #42
//    //   735: if_icmpge -> 749
//    //   738: aload_0
//    //   739: iload #7
//    //   741: iconst_1
//    //   742: iadd
//    //   743: putfield playerState : I
//    //   746: goto -> 754
//    //   749: aload_0
//    //   750: iconst_0
//    //   751: putfield playerState : I
//    //   754: aload_0
//    //   755: getfield playerY : F
//    //   758: aload_0
//    //   759: getfield playerHeight : F
//    //   762: fadd
//    //   763: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
//    //   766: invokeinterface getHeight : ()I
//    //   771: i2f
//    //   772: fcmpl
//    //   773: iflt -> 966
//    //   776: aload_0
//    //   777: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
//    //   780: invokeinterface getDeltaTime : ()F
//    //   785: fconst_1
//    //   786: fmul
//    //   787: ldc 60.0
//    //   789: fmul
//    //   790: putfield velocity : F
//    //   793: goto -> 966
//    //   796: aload #6
//    //   798: ldc 'costumeSelectedGame'
//    //   800: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   805: iconst_1
//    //   806: if_icmpne -> 821
//    //   809: aload_0
//    //   810: iconst_3
//    //   811: bipush #7
//    //   813: bipush #15
//    //   815: invokespecial playerRunState : (III)V
//    //   818: goto -> 966
//    //   821: aload #6
//    //   823: ldc 'costumeSelectedGame'
//    //   825: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   830: iconst_2
//    //   831: if_icmpne -> 846
//    //   834: aload_0
//    //   835: iconst_2
//    //   836: bipush #9
//    //   838: bipush #30
//    //   840: invokespecial playerRunState : (III)V
//    //   843: goto -> 966
//    //   846: aload #6
//    //   848: ldc 'costumeSelectedGame'
//    //   850: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   855: iconst_3
//    //   856: if_icmpne -> 870
//    //   859: aload_0
//    //   860: iconst_2
//    //   861: bipush #9
//    //   863: iconst_0
//    //   864: invokespecial playerRunState : (III)V
//    //   867: goto -> 966
//    //   870: aload #6
//    //   872: ldc 'costumeSelectedGame'
//    //   874: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   879: iconst_4
//    //   880: if_icmpne -> 895
//    //   883: aload_0
//    //   884: iconst_3
//    //   885: bipush #7
//    //   887: bipush #10
//    //   889: invokespecial playerRunState : (III)V
//    //   892: goto -> 966
//    //   895: aload #6
//    //   897: ldc 'costumeSelectedGame'
//    //   899: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   904: iconst_5
//    //   905: if_icmpne -> 919
//    //   908: aload_0
//    //   909: iconst_2
//    //   910: bipush #9
//    //   912: iconst_2
//    //   913: invokespecial playerRunState : (III)V
//    //   916: goto -> 966
//    //   919: aload #6
//    //   921: ldc 'costumeSelectedGame'
//    //   923: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   928: bipush #6
//    //   930: if_icmpne -> 944
//    //   933: aload_0
//    //   934: iconst_2
//    //   935: bipush #9
//    //   937: iconst_5
//    //   938: invokespecial playerRunState : (III)V
//    //   941: goto -> 966
//    //   944: aload #6
//    //   946: ldc 'costumeSelectedGame'
//    //   948: invokeinterface getInteger : (Ljava/lang/String;)I
//    //   953: bipush #7
//    //   955: if_icmpne -> 966
//    //   958: aload_0
//    //   959: iconst_3
//    //   960: bipush #7
//    //   962: iconst_0
//    //   963: invokespecial playerRunState : (III)V
//    //   966: aload_0
//    //   967: ldc 0.5
//    //   969: invokespecial worldYToScreenY : (F)F
//    //   972: fstore #9
//    //   974: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
//    //   977: invokeinterface getDeltaTime : ()F
//    //   982: fstore #10
//    //   984: aload_0
//    //   985: getfield velocity : F
//    //   988: fload #9
//    //   990: fload #10
//    //   992: fmul
//    //   993: ldc 60.0
//    //   995: fmul
//    //   996: fadd
//    //   997: fstore #9
//    //   999: aload_0
//    //   1000: fload #9
//    //   1002: putfield velocity : F
//    //   1005: aload_0
//    //   1006: getfield playerY : F
//    //   1009: fload #9
//    //   1011: fsub
//    //   1012: fstore #9
//    //   1014: aload_0
//    //   1015: fload #9
//    //   1017: putfield playerY : F
//    //   1020: fload #9
//    //   1022: aload_0
//    //   1023: ldc 75.0
//    //   1025: invokespecial worldYToScreenY : (F)F
//    //   1028: fcmpg
//    //   1029: ifgt -> 1042
//    //   1032: aload_0
//    //   1033: aload_0
//    //   1034: ldc 75.0
//    //   1036: invokespecial worldYToScreenY : (F)F
//    //   1039: putfield playerY : F
//    //   1042: return
//  }
//
//  void drawPlayerStart(SpriteBatch paramSpriteBatch, Preferences paramPreferences) {
//    if (paramPreferences.getInteger("costumeSelectedGame") == 0) {
//      this.playerX = worldXToScreenX(100.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
//      this.playerX = worldXToScreenX(65.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
//      this.playerX = worldXToScreenX(85.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
//      this.playerX = worldXToScreenX(85.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
//      this.playerX = worldXToScreenX(60.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
//      this.playerX = worldXToScreenX(90.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
//      this.playerX = worldXToScreenX(90.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
//      this.playerX = worldXToScreenX(80.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    }
//    paramSpriteBatch.draw((TextureRegion)this.playerRun[this.playerState], this.playerX, this.playerY, this.playerWidth, this.playerHeight);
//    paramSpriteBatch.draw((TextureRegion)this.tapToStart, worldXToScreenX(250.0F), worldYToScreenY(425.0F), worldXToScreenX(150.0F), worldYToScreenY(150.0F));
//  }
//
//  void drawPlayerWin(SpriteBatch paramSpriteBatch) {
//    float f1 = worldYToScreenY(0.5F);
//    float f2 = Gdx.graphics.getDeltaTime();
//    f1 = this.velocity + f1 * f2 * 60.0F;
//    this.velocity = f1;
//    f1 = this.playerY - f1;
//    this.playerY = f1;
//    if (f1 <= worldYToScreenY(75.0F)) {
//      f1 = worldYToScreenY(75.0F);
//      this.playerY = f1;
//      paramSpriteBatch.draw((TextureRegion)this.playerRun[this.playerState], this.playerX, f1, this.playerWidth, this.playerHeight);
//    } else {
//      paramSpriteBatch.draw((TextureRegion)this.playerRun[this.playerState], this.playerX, this.playerY, this.playerWidth, this.playerHeight);
//    }
//    if (this.playerY + this.playerHeight >= Gdx.graphics.getHeight())
//      this.playerY = Gdx.graphics.getHeight() - this.playerHeight;
//  }
//
//  float getPlayerHeightRect() {
//    return this.playerHeightRect;
//  }
//
//  Rectangle getPlayerRectangle() {
//    return this.playerRectangle;
//  }
//
//  float getPlayerWidthRect() {
//    return this.playerWidthRect;
//  }
//
//  float getPlayerXRect() {
//    return this.playerXRect;
//  }
//
//  float getPlayerY() {
//    return this.playerY;
//  }
//
//  void initializeValues(AssetManager paramAssetManager, Preferences paramPreferences) {
//    TextureAtlas textureAtlas1 = (TextureAtlas)paramAssetManager.get("main_game/main_game.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas2 = (TextureAtlas)paramAssetManager.get("shop/shop.atlas", TextureAtlas.class);
//    this.musicSoundsObject = new MusicSounds(paramAssetManager);
//    int i = paramPreferences.getInteger("costumeSelectedGame");
//    boolean bool1 = false;
//    byte b1 = 0;
//    boolean bool2 = false;
//    boolean bool3 = false;
//    boolean bool4 = false;
//    boolean bool5 = false;
//    boolean bool6 = false;
//    byte b2 = 0;
//    if (i == 0) {
//      this.playerRun = new TextureAtlas.AtlasRegion[43];
//      this.playerFaint = new TextureAtlas.AtlasRegion[43];
//      this.playerHurt = new TextureAtlas.AtlasRegion[43];
//      while (b2 < 43) {
//        this.playerRun[b2] = textureAtlas1.findRegion("player_run", b2);
//        this.playerFaint[b2] = textureAtlas1.findRegion("player_faint", b2);
//        this.playerHurt[b2] = textureAtlas1.findRegion("player_hurt", b2);
//        b2++;
//      }
//      setParameters(100, 600, 100, 155, 25, 60, 0);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
//      this.playerRun = new TextureAtlas.AtlasRegion[8];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[8];
//      b1 = 0;
//      while (true) {
//        b2 = bool1;
//        if (b1 < 8) {
//          this.playerRun[b1] = textureAtlas2.findRegion("robot_run", b1);
//          this.playerHurt[b1] = textureAtlas2.findRegion("robot_hurt", b1);
//          b1++;
//          continue;
//        }
//        break;
//      }
//      while (b2 < 10) {
//        this.playerFaint[b2] = textureAtlas2.findRegion("robot_faint", b2);
//        b2++;
//      }
//      setParameters(65, 600, 170, 170, 50, 60, 15);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
//      this.playerRun = new TextureAtlas.AtlasRegion[10];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[10];
//      for (b2 = b1; b2 < 10; b2++) {
//        this.playerRun[b2] = textureAtlas2.findRegion("knight_run", b2);
//        this.playerHurt[b2] = textureAtlas2.findRegion("knight_hurt", b2);
//        this.playerFaint[b2] = textureAtlas2.findRegion("knight_faint", b2);
//      }
//      setParameters(85, 600, 140, 170, 35, 60, 30);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
//      this.playerRun = new TextureAtlas.AtlasRegion[10];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[10];
//      for (b2 = bool2; b2 < 10; b2++) {
//        this.playerRun[b2] = textureAtlas2.findRegion("cowboy_run", b2);
//        this.playerHurt[b2] = textureAtlas2.findRegion("cowboy_hurt", b2);
//        this.playerFaint[b2] = textureAtlas2.findRegion("cowboy_faint", b2);
//      }
//      setParameters(85, 600, 120, 145, 35, 60, 30);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
//      this.playerRun = new TextureAtlas.AtlasRegion[8];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[8];
//      b1 = 0;
//      while (true) {
//        b2 = bool3;
//        if (b1 < 10) {
//          this.playerFaint[b1] = textureAtlas2.findRegion("cowgirl_faint", b1);
//          b1++;
//          continue;
//        }
//        break;
//      }
//      while (b2 < 8) {
//        this.playerRun[b2] = textureAtlas2.findRegion("cowgirl_run", b2);
//        this.playerHurt[b2] = textureAtlas2.findRegion("cowgirl_hurt", b2);
//        b2++;
//      }
//      setParameters(60, 600, 170, 145, 35, 60, 30);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
//      this.playerRun = new TextureAtlas.AtlasRegion[10];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[10];
//      for (b2 = bool4; b2 < 10; b2++) {
//        this.playerFaint[b2] = textureAtlas2.findRegion("ninja_male_faint", b2);
//        this.playerRun[b2] = textureAtlas2.findRegion("ninja_male_run", b2);
//        this.playerHurt[b2] = textureAtlas2.findRegion("ninja_male_hurt", b2);
//      }
//      setParameters(90, 600, 110, 140, 35, 60, 30);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
//      this.playerRun = new TextureAtlas.AtlasRegion[10];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[10];
//      for (b2 = bool5; b2 < 10; b2++) {
//        this.playerFaint[b2] = textureAtlas2.findRegion("ninja_female_faint", b2);
//        this.playerRun[b2] = textureAtlas2.findRegion("ninja_female_run", b2);
//        this.playerHurt[b2] = textureAtlas2.findRegion("ninja_female_hurt", b2);
//      }
//      setParameters(90, 600, 110, 150, 35, 60, 30);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
//      this.playerRun = new TextureAtlas.AtlasRegion[10];
//      this.playerFaint = new TextureAtlas.AtlasRegion[10];
//      this.playerHurt = new TextureAtlas.AtlasRegion[10];
//      for (b2 = bool6; b2 < 8; b2++) {
//        this.playerFaint[b2] = textureAtlas2.findRegion("dino_faint", b2);
//        this.playerRun[b2] = textureAtlas2.findRegion("dino_run", b2);
//        this.playerHurt[b2] = textureAtlas2.findRegion("dino_hurt", b2);
//      }
//      setParameters(80, 600, 190, 130, 35, 70, 30);
//    }
//    this.tapToStart = textureAtlas1.findRegion("tap_to_start_finger");
//  }
//
//  void resetPlayerStats(Preferences paramPreferences) {
//    if (paramPreferences.getInteger("costumeSelectedGame") == 0) {
//      this.playerX = worldXToScreenX(100.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
//      this.playerX = worldXToScreenX(65.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
//      this.playerX = worldXToScreenX(65.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
//      this.playerX = worldXToScreenX(85.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
//      this.playerX = worldXToScreenX(60.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
//      this.playerX = worldXToScreenX(90.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
//      this.playerX = worldXToScreenX(90.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
//      this.playerX = worldXToScreenX(80.0F);
//      this.playerY = worldYToScreenY(600.0F);
//    }
//    this.velocity = 0.0F;
//    this.playerFaintState = 0;
//  }
//}
//
//
///* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Player.class
// * Java compiler version: 6 (50.0)
// * JD-Core Version:       1.1.3
// */